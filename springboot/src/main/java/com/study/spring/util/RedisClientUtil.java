package com.study.spring.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis client util
 *
 * @author Jeffrey
 * @since 2017/3/2 14:19
 */
@Component
public class RedisClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisClientUtil.class);

    //从redis list pop数据的最大值
    private static final int MAX_POP_SIZE = 200;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * redis获取value
     */
    public <T> T get(String key, Class<T> T) {
        Object value = redisTemplate.opsForValue().get(getKey(key));
        if (value != null) {
            return (T) value;
        } else {
            return null;
        }
    }

    /**
     * redis添加数据
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(getKey(key), value);
    }

    /**
     * redis添加数据(设置超时)
     */
    public void set(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(getKey(key), value, time, unit);
    }

    /**
     * redis给key设置超时
     */
    public void expire(String key, long time, TimeUnit unit) {
        if (redisTemplate.hasKey(getKey(key))) {
            redisTemplate.expire(getKey(key), time, unit);
        }
    }

    /**
     * 向redis list push数据
     */
    public void rpush(String key, Object value) {
        redisTemplate.opsForList().rightPush(getKey(key), value);
    }

    /**
     * 从redis list pull一条数据
     */
    public <T> T lpop(String key, Class<T> T) {
        return (T) redisTemplate.opsForList().leftPop(getKey(key));
    }

    /**
     * 从redis list pull所有数据(根据max pop size做限制)
     */
    public <T> List<T> lpopAll(String key, Class<T> T) {
        List<T> list = new ArrayList<T>();
        ListOperations listOperations = redisTemplate.opsForList();
        T value = null;
        do {
            value = (T) listOperations.leftPop(getKey(key));
            if (value != null) {
                list.add(value);
            } else {
                break;
            }
        } while (list.size() < MAX_POP_SIZE);
        return list;
    }

    /**
     * 删除数据
     */
    public void delete(String key) {
        redisTemplate.delete(getKey(key));
    }

    /**
     * 批量删除(暂未用到该方法,未处理SOA前缀)
     */
    public void delete(Collection<String> key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取key(加上前缀)
     */
    private String getKey(String key) {
        return key;
    }
}
