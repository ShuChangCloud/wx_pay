package com.xmcc.service;

import com.xmcc.dto.ProductCategoryDto;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 17:11
 */
public interface ProductCategoryService {

    List<ProductCategoryDto> findAll();
}
