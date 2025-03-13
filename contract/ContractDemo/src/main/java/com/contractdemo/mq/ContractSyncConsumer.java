//package com.contractdemo.mq;
//
//import com.contractdemo.constant.MqConstants;
//import com.rabbitmq.client.Channel;
//import lombok.RequiredArgsConstructor;
//import org.elasticsearch.client.ElasticsearchClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@RequiredArgsConstructor
//public class ContractSyncConsumer {
//
//    @Autowired
//    private RestHighLevelClient esClient;
//
//    // 模式1：统一处理同步队列（推荐新业务使用）
//    @RabbitListener(queues = MqConstants.CONTRACT_SYNC_QUEUE)
//    @Transactional
//    public void handleSyncMessage(SyncMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        try {
//            processMessage(message);
//            channel.basicAck(tag, false); // 手动确认
//        } catch (Exception e) {
//            channel.basicNack(tag, false, true); // 重试
//            throw new RuntimeException("消息处理失败", e);
//        }
//    }
//
//    // 模式2：兼容原有队列（保留已有业务）
//    @RabbitListener(queues = MqConstants.CONTRACT_INCOME_INSERT_QUEUE)
//    public void handleInsertMessage(Contract contract) {
//        esOperations.save(contract);
//    }
//
//    @RabbitListener(queues = MqConstants.CONTRACT_INCOME_DELETE_QUEUE)
//    public void handleDeleteMessage(Long contractId) {
//        esOperations.delete(contractId.toString(), IndexCoordinates.of("contract"));
//    }
//
//    private void processMessage(SyncMessage message) {
//        switch (message.getOperationType()) {
//            case "insert":
//            case "update":
//                esOperations.save(message.getData());
//                break;
//            case "delete":
//                esOperations.delete(
//                        message.getContractId().toString(),
//                        IndexCoordinates.of("contract")
//                );
//                break;
//            default:
//                throw new IllegalArgumentException("非法操作类型");
//        }
//    }
//}