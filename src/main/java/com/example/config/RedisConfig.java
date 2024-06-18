package com.example.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
@ConfigurationPropertiesScan
public class RedisConfig {

    /**
     * 映射application配置的redis属性
     */
    @ConfigurationProperties(prefix = "spring.data.redis")
    @Data
    public static class Redis {

        private Integer database;

        private String host;

        private Integer port;

        private String password;

        private Integer timeout;

        private Jedis jedis = new Jedis();

        @Data
        public class Jedis {

            private Pool pool = new Pool();

            @Data
            public class Pool {

                // required : true-允许反序列化使用别名，false-禁止反序列化使用该别别名
                @JsonProperty(value = "max-idle", required = true)
                private Integer maxIdle;

                @JsonProperty(value = "max-active", required = true)
                private Integer maxActive;

                @JsonProperty(value = "max-wait", required = true)
                private Integer maxWait;

                @JsonProperty(value = "min-idle", required = true)
                private Integer minIdle;

            }

        }

    }

    /**
     * 建立连接
     *
     * @return
     */
    @Bean
    public JedisPool jedisPool(Redis redis) {
        Redis.Jedis.Pool pool = redis.getJedis().getPool();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMaxWait(Duration.ofMillis(pool.getMaxWait()));
        jedisPoolConfig.setMinIdle(pool.getMinIdle());
        return new JedisPool(jedisPoolConfig, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword());

    }

}
