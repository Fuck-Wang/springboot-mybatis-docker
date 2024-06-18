package com.example.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClientFactory;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.internal.MongoClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import java.util.List;


@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

//    @Bean
//    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
//        MongoDatabase database = mongoClient.getDatabase(this.database);
//        return GridFSBuckets.create(database);
//    }

    /**
     * 这个bean必须配置，并从yml配置中注入host和port，使mongoClient是该host-port的客户端
     * @return .
     */
//    @Bean
//    public MongoClientFactoryBean mongoClient() {
//        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
//        mongoClientFactoryBean.setHost(host);
//        mongoClientFactoryBean.setPort(port);
//        return mongoClientFactoryBean;
//    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    /**
     * 配置连接请求并通过用户名和密码验证
     * @param builder
     */
    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.credential(MongoCredential.createCredential(username, database, password.toCharArray()))
                .applyToClusterSettings(settings -> {
                    // ServerAddress() 无参构造函数中设置了默认的host:port，如果没有显式地注入配置的host:port，
                    // 就会使用默认值127.0.0.1:27017，该host:port在控制台日志中有打印。
                    settings.hosts(List.of(new ServerAddress(host, port)));
                });
    }

    @Bean
    public GridFSBucket gridFSBucket(MongoDatabaseFactory mongoDbFactory) {
        MongoDatabase database = mongoDbFactory.getMongoDatabase();
        return GridFSBuckets.create(database);
    }


}
