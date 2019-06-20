package com.xmcc.common;

import lombok.Getter;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 16:29
 */
@Getter//这里是公共的定义 只需要get方法就可以了
public enum OrderEnums {
    NEW(0,"新建订单"),
    FINSH(1,"已完成订单"),
    CANCEL(2,"已取消"),
    OPENID_ERROR(10,"订单openid输入有误"),
    ORDER_NOT_EXITS(100,"订单不存在"),
    FINSH_CANCEL(1000,"订单已经完成,或者已经取消,无法取消"),
    AMOUNT_CHECK_ERROR(10000,"微信支付订单金额与数据库查询的不一致");
    private int code;
    private String msg;

    OrderEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
