package com.contractdemo.mq;

import cn.hutool.json.JSONUtil;
import com.contractdemo.entity.IncomeContract;
import com.contractdemo.mapper.IncomeContractMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class 
template.setBeforePublishPostProcessors(message -> {
    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 消息持久化
    log.info("发送消息到交换机：{}，路由键：{}，消息体：{}", 
        message.getMessageProperties().getReceivedExchange(),
        message.getMessageProperties().getReceivedRoutingKey(),
        new String(message.getBody()));
    return message;
}); {
    private final RestHighLevelClient client;

    @Autowired
    private IncomeContractMapper incomeContractMapper;

    public static final String INCOME_INDEX="income_contract";


    // 处理新增
    @RabbitListener(queues = "contract.insert.queue")
    public void handleInsert(Message message, Channel channel) throws IOException {
        Integer contractId = JSONUtil.toBean(new String(message.getBody()), Integer.class);
        log.info("contract.insert.queue :::: 收到消息 contractId: {}", contractId);
        
        try {
            IncomeContract contract = incomeContractMapper.selectById(contractId);
            if(contract == null){
                log.error("合同不存在 ID: {}", contractId);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                return;
            }

            IndexRequest request = new IndexRequest(INCOME_INDEX).id(contract.getId().toString());
            request.source(JSONUtil.toJsonStr(contract), XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
            
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("新增合约同步成功：{}", contract.getId());
        } catch (Exception e) {
            log.error("处理新增合约失败 ID: {}", contractId, e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    // 处理更新
    @RabbitListener(queues = "contract.update.queue")
    public void handleUpdate(@Payload Integer contractId, Message message, Channel channel) throws IOException {
        log.info("contract.update.queue : 收到消息 contractId: {}", contractId);
        
        try {
            IncomeContract contract = incomeContractMapper.selectById(contractId);
            if(contract == null){
                log.error("合同不存在 ID: {}", contractId);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                return;
            }

            IndexRequest request = new IndexRequest(INCOME_INDEX).id(contract.getId().toString());
            request.source(JSONUtil.toJsonStr(contract), XContentType.JSON);
            client.index(request, RequestOptions.DEFAULT);
            
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("更新合约同步成功：{}", contract.getId());
        } catch (Exception e) {
            log.error("处理更新合约失败 ID: {}", contractId, e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    // 处理删除（示例）
    // @RabbitListener(queues = "contract.delete.queue")
    // public void handleDelete(Message message, Channel channel) throws IOException {
    //     try {
    //         Integer contractId = JSONUtil.toBean(new String(message.getBody()), Integer.class);
    //         // 删除逻辑...
    //         channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    //     } catch (Exception e) {
    //         channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    //     }
    // }
}