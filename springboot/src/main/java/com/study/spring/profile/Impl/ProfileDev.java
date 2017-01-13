package com.study.spring.profile.Impl;

import com.study.spring.annotation.profile.DevEnv;
import com.study.spring.profile.IProfile;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
@Component
@DevEnv
@Primary
public class ProfileDev implements IProfile {

  @Override
  public String getName() {
    return "this is ProfileDev!";
  }
}
