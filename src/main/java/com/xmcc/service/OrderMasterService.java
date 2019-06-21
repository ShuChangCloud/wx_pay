package com.xmcc.service;

import com.xmcc.dto.OrderMasterDto;
import com.xmcc.util.ResultResponse;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 20:22
 */
public interface OrderMasterService {

    /**
     * 生成订单
     * @param masterDto
     * @return
     */
    ResultResponse insertOrder(OrderMasterDto masterDto);
}
