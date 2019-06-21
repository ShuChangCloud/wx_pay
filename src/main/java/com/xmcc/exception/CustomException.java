package com.xmcc.exception;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 16:49
 */
import com.xmcc.common.ResultEnums;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private int  code;

    public CustomException(int code,String message){
        super(message);
        this.code = code;
    }
    public CustomException(String message){
       this(ResultEnums.FAIL.getCode(),message);
    }

}
