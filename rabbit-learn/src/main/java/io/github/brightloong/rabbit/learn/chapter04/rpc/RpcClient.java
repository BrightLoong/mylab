package io.github.brightloong.rabbit.learn.chapter04.rpc;

import com.rabbitmq.client.*;
import io.github.brightloong.rabbit.learn.RabbitUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @author BrightLoong
 * @date 2018/10/30 22:21
 * @description
 */
public class RpcClient {
    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private Channel channel;
    private String replyQueueName;
    private Consumer consumer;
    String corrId;
    String response;

    public RpcClient() throws IOException, TimeoutException {
        channel = RabbitUtils.getChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new DefaultConsumer(channel) {
            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response = new String(body);
                }
            }
        };
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message, String corrId) throws IOException {
        this.corrId = corrId;
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", RPC_QUEUE_NAME, properties, message.getBytes());
        while (true) {
            if (response != null) {
                break;
            }
        }
        return response;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        RpcClient rpcClient = new RpcClient();
        String call = rpcClient.call("30",  UUID.randomUUID().toString());
        System.out.println(call);
    }
}
