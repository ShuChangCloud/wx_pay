package com.xmcc.service;

import com.xmcc.entity.OrderDetail;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 20:22
 */
public interface OrderDetailService {

    void batchInsert(List<OrderDetail> list);
}
