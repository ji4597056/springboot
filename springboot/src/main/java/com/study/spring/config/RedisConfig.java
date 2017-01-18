package com.study.spring.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.annotation.profile.RedisEnv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.reflect.Method;

/**
 * redis(包含缓存)配置
 *
 * @author Jeffrey
 * @since 2017/01/10 13:03
 */
@Configuration
@EnableCaching
@EnableTransactionManagement
@RedisEnv
public class RedisConfig extends CachingConfigurerSupport {

  private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

  @Value("${spring.redis.expire-time:300}")
  private long expireTime;

  /**
   * 生成key策略
   *
   * @return KeyGenerator
   */
  @Bean
  public KeyGenerator keyGenerator() {
    return new KeyGenerator() {
      @Override
      public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(method.getName());
        for (Object obj : params) {
          sb.append(obj.toString());
        }
        return sb.toString();
      }
    };
  }

  /**
   * 缓存管理
   *
   * @param redisTemplate
   * @return CacheManager
   */
  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheManager crm = new RedisCacheManager(redisTemplate);
    crm.setDefaultExpiration(expireTime); //缓存过期时间（秒）
    return crm;
  }

  /**
   * RedisTemplate配置
   *
   * @param factory
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate template = new StringRedisTemplate(factory);
    // 用jackson序列化实体类
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }
}
