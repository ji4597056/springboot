package com.study.spring.web;

import com.study.spring.config.Configuation;
import com.study.spring.profile.IProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @Autowired private IProfile profile;

  @Autowired private Configuation configuation;

  @RequestMapping(value = "name", method = RequestMethod.GET)
  public String getName() {
    return name;
  }

  @RequestMapping(value = "config", method = RequestMethod.GET)
  public Configuation getConfiguation() {
    return configuation;
  }

  @RequestMapping(value = "class/name", method = RequestMethod.GET)
  public String getProfileName() {
    return profile.getName();
  }

  @RequestMapping(value = "logger/level", method = RequestMethod.GET)
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
