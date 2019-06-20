package com.xmcc.controller;

import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.service.ProductInfoService;
import com.xmcc.util.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 17:07
 */
@RestController
@RequestMapping("buyer/product")
@Api(description = "商品信息接口")//使用swagger2的注解对类描述
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;
    @GetMapping("list")
    @ApiOperation(value = "查询商品列表")//使用swagger2的注解对方法接口描述
    public ResultResponse list(){
        List<ProductCategoryDto> dtos = productInfoService.queryList();
        if (dtos.isEmpty()) {
            return ResultResponse.fail();
        }
        return ResultResponse.success(dtos);
    }
}
