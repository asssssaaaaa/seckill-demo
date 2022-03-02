package com.xxxx.seckill.utils;

import java.util.UUID;

/**
 * @author QBX
 * @date 2022/2/27
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
