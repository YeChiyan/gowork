package com.contractdemo.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AMQP;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.interceptor.RejectAndDontRequeueRecoverer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

import static com.contractdemo.config.DlxConfig.BUSINESS_QUEUEA_NAME;
import static com.contractdemo.config.DlxConfig.BUSINESS_QUEUEB_NAME;

@Slf4j
@Component
public class BusinessMessageReceiver {
    @RabbitListener(queues = BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        String msg = new String(message.getBody());
        
        // 初始化重试计数器（首次消费时没有这个header）
        int retryCount = headers.containsKey("x-retry-count") ? 
            (Integer) headers.get("x-retry-count") : 0;

        try {
            log.info("第{}次处理消息: {}", retryCount + 1, msg);
            
            if (msg.contains("dead")) {
                throw new RuntimeException("业务处理异常");
            }
            
            channel.basicAck(properties.getDeliveryTag(), false);
        } catch (Exception e) {
            if (retryCount >= 2) { // 已重试3次（0-based计数）
                log.error("消息已达最大重试次数，转入死信队列. 内容: {}", msg);
                channel.basicNack(properties.getDeliveryTag(), false, false);
            } else {
                // 关键修改：手动重新发布消息（携带更新后的header）
                headers.put("x-retry-count", retryCount + 1);
                channel.basicPublish(
                    "", 
                    properties.getConsumerQueue(), 
                    new AMQP.BasicProperties.Builder()
                        .headers(headers)
                        .expiration("5000") // 设置消息TTL
                        .build(),
                    message.getBody()
                );
                channel.basicAck(properties.getDeliveryTag(), false); // 确认原消息
                log.warn("已重新投递消息. 重试次数: {}/3", retryCount + 1);
            }
        }
    }

    @RabbitListener(queues = BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message,Channel channel) throws IOException {
        log.info("收到 B 消息：{}"+message.getBody().toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}

@Configuration
public class RetryConfig {
    
    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless()
            .maxAttempts(3) // 总尝试次数=初始消费+重试次数
            .backOffOptions(1000, 2.0, 5000) // 初始间隔1s，倍数增长，最大间隔5s
            .recoverer(new RejectAndDontRequeueRecoverer()) // 最终失败时拒绝消息
            .build();
    }
}
