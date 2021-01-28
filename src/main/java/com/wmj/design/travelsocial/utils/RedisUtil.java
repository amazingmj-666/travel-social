package com.wmj.design.travelsocial.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wmj
 * @date 2021/1/26 17:04
 * @Description
 */
@Slf4j
@Component
public class RedisUtil {

    private static RedisTemplate redisTemplate;

    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public static long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子应大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子应大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    public static boolean hmSet(String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("hmSet报错,{}", e);
            return false;
        }
    }

    public static Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Resource
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        /*RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);*/
        RedisUtil.redisTemplate = redisTemplate;
    }
}
