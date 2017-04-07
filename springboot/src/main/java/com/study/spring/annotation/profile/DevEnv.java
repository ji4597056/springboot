package com.study.spring.annotation.profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Profile;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:42
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev")
public @interface DevEnv {

}
