package com.xmcc.service.impl;

import com.xmcc.dao.BatchDaoImpl;
import com.xmcc.entity.OrderDetail;
import com.xmcc.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 20:22
 */

@Service
@Transactional //加入事物管理
public class OrderDetailServiceImpl extends BatchDaoImpl<OrderDetail> implements OrderDetailService {

    @Override
    public void batchInsert(List<OrderDetail> list) {
        super.batchInsert(list);
    }


}
