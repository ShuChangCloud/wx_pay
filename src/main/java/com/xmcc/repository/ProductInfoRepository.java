package com.xmcc.repository;

import com.xmcc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 17:14
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 通过商品状态和类型查询正在上架的商品
     *
     * @param status        商品状态
     * @param categoryLists 类型列表
     * @return 商品信息
     */
    List<ProductInfo> findByProductStatusAndCategoryTypeIn(Integer status, List<Integer> categoryLists);
}
