package com.study.spring.web;

import com.study.spring.config.DemoConfig;
import com.study.spring.profile.IProfile;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:50
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Value("${test.name:无名}")
    private String name;

    @Autowired
    private IProfile profile;

    @Autowired
    private DemoConfig configuation;

    @GetMapping("name")
    @ApiOperation(value = "查询test.name变量值")
    public String getName() {
        return name;
    }

    @GetMapping("config")
    @ApiOperation(value = "查询config配置信息")
    public DemoConfig getConfiguation() {
        return configuation;
    }

    @GetMapping("/class/name")
    @ApiOperation(value = "查询Iprofile的实现类名")
    public String getProfileName() {
        return profile.getName();
    }

    @GetMapping("/logger/level")
    @ApiOperation(value = "查询logger的level")
    public String getLoggerLevel() {
        String level = "";
        String prefix = "logger level is {}";
        if (logger.isDebugEnabled()) {
            level = "debug";
        } else if (logger.isInfoEnabled()) {
            level = "info";
        } else if (logger.isWarnEnabled()) {
            level = "warn";
        } else if (logger.isErrorEnabled()) {
            level = "error";
        }
        logger.debug(prefix, "debug");
        logger.info(prefix, "info");
        logger.warn(prefix, "warn");
        logger.error(prefix, "error");
        return "logger level : " + level;
    }
}
