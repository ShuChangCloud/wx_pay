package com.xmcc.service;

import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.entity.ProductInfo;

import java.util.List;
import java.util.Map;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 19:55
 */
public interface ProductInfoService {

    List<ProductCategoryDto> queryList();

}
