package com.example.rabbitmq;

import com.rabbitmq.client.*;

public class Consumer {

    public static void main(String[] args) throws Exception{
        String queueName = "ck_queue_name";
        ConnectionFactory factory = new ConnectionFactory() {{
            setHost("127.0.0.1");
            setPassword("guest");
            setUsername("guest");
            setPort(5672);
        }};
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
        channel.basicConsume(queueName, true, deliverCallback, cancelCallback);
    }
}
