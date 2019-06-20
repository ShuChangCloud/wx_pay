package com.xmcc.common;

import lombok.Getter;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 16:29
 */
@Getter//这里是公共的定义 只需要get方法就可以了
public enum ProductEnums {
    PRODUCT_NOT_ENOUGH(1,"商品库存不足");
    private int code;
    private String msg;

    ProductEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
