package com.example.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) throws Exception{
        String exchangeName = "ck_exchange_name";
        String queueName = "ck_queue_name";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /*
         * 1.交换机名称
         * 2.交换机类型：direct,topic,fanout,headers
         * 3.交换机是否需要持久化，true: 交换机元数据需要持久化
         * 4.交换机没有队列绑定时是否需要删除，false:不删除
         * 5.Map<String, Object>类型，指定交换机其他的机构化参数
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);

        /*
        * 1.队列名称
        * 2.队列元数据信息是否需要持久化，true：持久化队列元数据，不是持久化消息
        * 3.是否私有，true：只有创建她的应用程序才能消费消息
        * 4.队列在没有消费者订阅的情况下是否自动删除
        * 5.队列的结构化信息：死信队列，磁盘队列会用到
        *
         */
        channel.queueDeclare(queueName, true, false, false, null);
        /*
        * 1.队列名称
        * 2.交换机名称
        * 3.路由键，直连模式下可以写队列名称
         */
        channel.queueBind(queueName, exchangeName, queueName);

        String message = "我是你爸爸";
        /*
        * 1.交换机名称
        * 2.对列名称
        * 3.其他参数信息
        * 4.发送消息的游戏体
         */
        channel.basicPublish(exchangeName, queueName, null, message.getBytes());
        if (channel.isOpen()) {
            channel.close();
        }
        if (connection.isOpen()) {
            connection.close();
        }
    }

}
