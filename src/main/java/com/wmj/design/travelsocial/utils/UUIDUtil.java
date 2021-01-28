package com.wmj.design.travelsocial.utils;

import java.util.UUID;

/**
 * @author wmj
 * @date 2021/1/26 17:53
 * @Description
 */
public class UUIDUtil {

    // 生成UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
