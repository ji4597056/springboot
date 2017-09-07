package com.study.spring.util;

import java.util.UUID;

/**
 * primary key util
 *
 * @author Jeffrey
 * @since 2017/3/2 16:49
 */
public class PrimaryKeyUtils {

    private PrimaryKeyUtils() {
        throw new AssertionError("No instances for you!");
    }

    /**
     * 获取uuid
     *
     * @return uuid
     */
    public static String getOriginUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取uuid，去除-
     *
     * @return uuid
     */
    public static String getUUID() {
        StringBuilder uuid = new StringBuilder();
        String origin = UUID.randomUUID().toString();
        // 去掉"-"符号
        uuid.append(origin.substring(0, 8)).append(origin.substring(9, 13))
            .append(origin.substring(14, 18)).append(origin.substring(19, 23))
            .append(origin.substring(24));
        return uuid.toString();
    }

}
