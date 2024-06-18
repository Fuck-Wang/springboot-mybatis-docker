package com.example.rabbitmq.direct;

import com.example.rabbitmq.ConnectionFactoryConfig;
import com.rabbitmq.client.*;

public class Consumer {

    public static void main(String[] args) throws Exception{
        String queueName1 = "ck_queue_name_1";
        String queueName2 = "ck_queue_name_2";
        String queueName3 = "ck_queue_name_3";
        String queueName4 = "ck_queue_name_4";
        ConnectionFactory factory = ConnectionFactoryConfig.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 接受消息回调函数
        DeliverCallback deliverCallback = (consumerTarget, message) -> {
            System.out.println("接收到消息： " + new String(message.getBody()));
        };
        // 取消接收消息回调函数
        CancelCallback cancelCallback = consumerTarget -> {
            System.out.println("中断接收消息！");
        };
        /*
        * 1. 队列名称
        * 2.是否自动应答
        * 3.接收消息回调函数
        * 4.取消接收消息回调函数
         */
        channel.basicConsume(queueName1, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName2, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName3, true, deliverCallback, cancelCallback);
        channel.basicConsume(queueName4, true, deliverCallback, cancelCallback);
    }
}
