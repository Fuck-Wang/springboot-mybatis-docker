package com.example.entity.util;

import com.example.config.RedisConfig;
import com.example.entity.exception.BadRequestException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Component
public class RedisUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    public JedisPool jedisPool;

    /**
     * 设值
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception ex) {
            logger.error("set value异常，异常信息{}", ex);
//            throw new BadRequestException(400, "Redis", "set value error.");

            System.out.println(ex.getCause());
            return "";
        } finally {
            Optional.ofNullable(jedis).ifPresent(Jedis::close);
        }
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public Object get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception ex) {
            logger.error("get value error, message : {}", ex.getMessage());
            throw new BadRequestException(400, "Redis", "get value error.");
        } finally {
            Optional.ofNullable(jedis).ifPresent(Jedis::close);
        }

    }

    /**
     * <p>删除指定的keys， 也可以传入一个包含key的数组</p>
     *
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(keys);
        } catch (Exception ex) {
            logger.error("del value error, message : {}", ex.getMessage());
            throw new BadRequestException(400, "Redis", "del value error.");
        } finally {
            Optional.ofNullable(jedis).ifPresent(Jedis::close);
        }
    }


}
