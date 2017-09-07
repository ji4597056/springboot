package com.study.spring.web;

import com.study.spring.service.cache.CacheService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cache test controller
 *
 * @author Jeffrey
 * @since 2017/05/31 16:47
 */
@RestController
@RequestMapping("cache/value")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping()
    @ApiOperation("set value")
    public void setValue(@RequestBody String value) {
        cacheService.setValue(value);
    }

    @GetMapping()
    @ApiOperation("get value")
    public String getValue() {
        return cacheService.getValue();
    }

    @PutMapping()
    @ApiOperation("update value")
    public void updateValue(@RequestBody String value) {
        cacheService.updateValue(value);
    }

    @DeleteMapping()
    @ApiOperation("delete value")
    public void deleteValue() {
        cacheService.deleteValue();
    }
}
