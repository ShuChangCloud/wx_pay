package com.xmcc.common;

import lombok.Getter;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 16:29
 */
@Getter//这里是公共的定义 只需要get方法就可以了
public enum ResultEnums {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    PRODUCT_UP(0,"正常"),
    PRODUCT_DOWN(1,"商品下架"),
    PRODUCT_NOT_ENOUGH(10,"商品库存不足"),
    PARAM_ERROR(1,"参数异常"),
    NOT_EXITS(1,"商品不存在");
    private int code;
    private String msg;

    ResultEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
