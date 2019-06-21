package com.xmcc.service;

import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.entity.ProductInfo;
import com.xmcc.util.ResultResponse;

import java.util.List;
import java.util.Map;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 19:55
 */
public interface ProductInfoService {

    /**
     * 查询所有的商品类型
     * @return
     */
    List<ProductCategoryDto> queryList();

    /**
     * 根据商品的id得到商品的信息
     * @param productId
     * @return
     */
    ResultResponse<ProductInfo>  queryById(String productId);


    /**
     * 更新商品信息
     * @param info 待更新的商品
     */
    void updateProductInfo(ProductInfo info);
}
