package org.lxr.util;

import java.util.UUID;

/**
 * @description: 生成随机id
 * @create: 2019-01-29 21:22
 **/
public class IDUtil
{
    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
