package com.example.demo.utils.CookieUtils;

import java.util.UUID;

/**
 * UUID生成工具类
 */
public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
