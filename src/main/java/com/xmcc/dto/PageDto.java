package com.xmcc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-24 9:24
 */


@Data
@ApiModel("分页查询的实体类")//swagger 参数的描述信息
public class PageDto {

    @ApiModelProperty(value = "当前第几页,jpa的当前页数是从0开始的", dataType = "int")
    private int page = 0;
    @ApiModelProperty(value = "每页显示的条数", dataType = "int")
    private int size = 10;
    @ApiModelProperty(value = "客户的openID", dataType = "String")
    private String openid;
}
