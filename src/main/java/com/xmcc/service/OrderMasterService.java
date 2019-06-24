package com.xmcc.service;

import com.xmcc.dto.OrderMasterDto;
import com.xmcc.dto.OrderMasterDto1;
import com.xmcc.dto.PageDto;
import com.xmcc.entity.OrderMaster;
import com.xmcc.util.ResultResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 20:22
 */
public interface OrderMasterService {

    /**
     * 生成订单
     *
     * @param masterDto
     * @return
     */
    ResultResponse insertOrder(OrderMasterDto masterDto);


    List<OrderMaster> findOrderByOpenidPageable(PageDto pageDto);

    OrderMasterDto1 findOrderDetail(String openid, String orderId);

    @Transactional
    boolean cancelOrder(String openid, String orderId);

}
