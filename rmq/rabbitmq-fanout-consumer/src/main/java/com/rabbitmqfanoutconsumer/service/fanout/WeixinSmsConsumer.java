package com.rabbitmqfanoutconsumer.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


@RabbitListener(queues ={"sms.fanout.queue"})
public class FanoutSmsConsumer {

    @RabbitHandler
    public void reciveMsg(String message){
        System.out.println("sms.fanout.queue收到了消息："+message);
    }
}

