package com.xmcc.service.impl;


import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.entity.ProductCategory;
import com.xmcc.repository.ProductCategoryRepository;
import com.xmcc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 17:10
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 查找全部的商品类别信息
     * @return
     */
    @Override
    public List<ProductCategoryDto> findAll() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        List<ProductCategoryDto> categoryDtos = productCategoryList.stream()
                .map(ProductCategoryDto::build)
                .collect(toList());
        return categoryDtos;
    }
}
