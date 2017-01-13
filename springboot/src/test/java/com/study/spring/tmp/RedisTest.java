package com.study.spring.tmp;

import com.study.spring.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author Jeffrey
 * @since 2017/01/10 10:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("redis")
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSaveString() throws Exception {
        // 保存字符串
        String key = "test_string_value";
        redisTemplate.opsForValue().set(key, "111", 1, TimeUnit.MINUTES);
        Assert.assertEquals("111", redisTemplate.opsForValue().get(key));
    }

    @Test
    public void testSaveObject() throws Exception {
        //保存对象
        String key = "test_object_value";
        Person person = new Person(1, "张三");
        redisTemplate.opsForValue().set(key, person, 1, TimeUnit.MINUTES);
        Person personFromRedis = (Person) redisTemplate.opsForValue().get(key);
        Assert.assertEquals(person, personFromRedis); //不是同一个对象
    }
}
