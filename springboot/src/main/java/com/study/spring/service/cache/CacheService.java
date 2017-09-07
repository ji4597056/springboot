package com.study.spring.service.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Jeffrey
 * @since 2017/05/31 16:33
 */
@Service("cacheService")
public class CacheService {

    public volatile String value;

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    private static final String CACHE_NAME = "userCache";

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void setValue(String value) {
        sleep();
        this.value = value;
    }

    @Cacheable(value = CACHE_NAME, key = "'test_key'")
    public String getValue() throws RuntimeException {
        sleep();
        if (value == null) {
            throw new RuntimeException("value is null!");
        }
        return this.value;
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void updateValue(String value) {
        sleep();
        if (value == null) {
            throw new RuntimeException("value is null!");
        }
        this.value = value;
    }

    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void deleteValue() {
        sleep();
        this.value = null;
    }

    /**
     * 模拟远程调用
     */
    private void sleep(Long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException("thread interrupted!", e);
        }
        logger.info("thread sleep {} ms.", sleepTime);
    }

    private void sleep() {
        sleep(1000L);
    }
}
