package com.study.spring.profile;

/**
 * @author Jeffrey
 * @since 2017/1/9 15:49
 */
public interface IProfile {

    default String getName() {
        return "this is IProfile!";
    }
}
