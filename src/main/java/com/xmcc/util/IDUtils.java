package com.xmcc.util;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 16:48
 */
import java.util.UUID;

/**
 * 订单id生成的问题很复杂 我们再以后分库分表中来学习 这儿简单起见
 */
public class IDUtils {

    public static String createIdbyUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
