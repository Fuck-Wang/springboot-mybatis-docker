package com.example.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 配置rabbitmq基础信息
 */
public class ConnectionFactoryConfig {

    /**
     * 实例化rabbitmq连接工厂对象
     * @return
     */
    public static ConnectionFactory getFactory() {
        return new ConnectionFactory() {{
            setHost("127.0.0.1");
            setPassword("guest");
            setUsername("guest");
            setPort(5672);
        }};
    }

}
