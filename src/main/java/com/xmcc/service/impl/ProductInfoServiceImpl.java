package com.xmcc.service.impl;


import com.xmcc.common.ResultEnums;
import com.xmcc.dto.ProductCategoryDto;
import com.xmcc.dto.ProductInfoDto;
import com.xmcc.entity.ProductInfo;
import com.xmcc.repository.ProductInfoRepository;
import com.xmcc.service.ProductCategoryService;
import com.xmcc.service.ProductInfoService;
import com.xmcc.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-20 19:57
 */

@Service

public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Override
    public List<ProductCategoryDto> queryList() {
        //所有商品分类信息的dto
        List<ProductCategoryDto> categoryDtoList = productCategoryService.findAll();
        //所有商品分类信息的类别id集合
        List<Integer> typeLists = categoryDtoList.stream()
                .map(ProductCategoryDto::getCategoryType)
                .collect(toList());
        //所有上架的商品信息
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatusAndCategoryTypeIn(ResultEnums.PRODUCT_UP.getCode(), typeLists);
        //多线程遍历 取出每个商品类目编号对应的 商品列表 设置进入类目中
        List<ProductCategoryDto> finalResultList = categoryDtoList.parallelStream().map(categorydto -> {
            categorydto.setProductInfoDtoList(
                    productInfoList.stream().
                    filter(productInfo -> productInfo.getCategoryType() == categorydto.getCategoryType())
                    .map(ProductInfoDto::build)
                    .collect(toList()));
            return categorydto;
        }).collect(toList());
        return finalResultList;
    }

    @Override
    public ResultResponse<ProductInfo> queryById(String productId) {
        //检查productId参数是否为空
        if (StringUtils.isEmpty(productId)) {
            ResultResponse.fail(ResultEnums.PARAM_ERROR.getMsg());
        }

        Optional<ProductInfo> info = productInfoRepository.findById(productId);
        //检查商品信息是否为空
        if (!info.isPresent()) {
            ResultResponse.fail(ResultEnums.NOT_EXITS.getMsg());
        }
        ProductInfo productInfo = info.get();

        //检查商品状态
        if (productInfo.getProductStatus()== ResultEnums.PRODUCT_DOWN.getCode()) {
            ResultResponse.fail(ResultEnums.PRODUCT_DOWN.getMsg());
        }
        return ResultResponse.success(productInfo);
    }

    @Override
    @Transactional
    public void updateProductInfo(ProductInfo info) {
        productInfoRepository.save(info);
    }
}
