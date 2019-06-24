package com.xmcc.repository;

import com.xmcc.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 17:29
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
