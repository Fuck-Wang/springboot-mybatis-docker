package com.example.rabbitmq.fanout;

import com.example.rabbitmq.ConnectionFactoryConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) throws Exception{
        String exchangeName = "ck_exchange_fanout_name";
        String queueName1 = "ck_queue__fanout_name_1";
        String queueName2 = "ck_queue__fanout_name_2";
        String queueName3 = "ck_queue__fanout_name_3";
        String queueName4 = "ck_queue__fanout_name_4";
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        ConnectionFactory factory = ConnectionFactoryConfig.getFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /*
         * 1.交换机名称
         * 2.交换机类型：direct（完全匹配路由键）,topic（模糊匹配路由键）,fanout（queue直接绑定交换机exchange，不通过路由键，所以会收到exchange的群发）,
         * headers（同direct，性能差很多）
         * 3.交换机是否需要持久化，true: 交换机元数据需要持久化
         * 4.交换机没有队列绑定时是否需要删除，false:不删除
         * 5.Map<String, Object>类型，指定交换机其他的机构化参数
         */
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT, true, false, null);

        /*
        * 1.队列名称
        * 2.队列元数据信息是否需要持久化，true：持久化队列元数据，不是持久化消息
        * 3.是否私有，true：只有创建她的应用程序才能消费消息
        * 4.队列在没有消费者订阅的情况下是否自动删除
        * 5.队列的结构化信息：死信队列，磁盘队列会用到
        *
         */
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueDeclare(queueName2, true, false, false, null);
        channel.queueDeclare(queueName3, true, false, false, null);
        channel.queueDeclare(queueName4, true, false, false, null);
        /*
        * 1.队列名称
        * 2.交换机名称
        * 3.路由键，直连模式下可以写队列名称
         */
        channel.queueBind(queueName1, exchangeName, key1);
        channel.queueBind(queueName2, exchangeName, key1);
        channel.queueBind(queueName3, exchangeName, key2);
        channel.queueBind(queueName4, exchangeName, key3);

        String message = "我是你爸爸";
        /*
        * channel按照路由键向queue发布消息，queue1和queue2连接着key1，所以已收到同样的消息：key1.message
        * 1.交换机名称
        * 2.路由键
        * 3.其他参数信息
        * 4.发送消息的游戏体
         */
        channel.basicPublish(exchangeName, key1, null, "key1.message".getBytes());
        channel.basicPublish(exchangeName, key2, null, "key2.message".getBytes());
        if (channel.isOpen()) {
            channel.close();
        }
        if (connection.isOpen()) {
            connection.close();
        }
    }

}
