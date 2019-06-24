package com.xmcc.dto;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 16:47
 */

import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单参数实体类 带有orderDetailList
 */
@Data
@ApiModel("订单参数实体类 带有orderDetailList")
public class OrderMasterDto1 extends OrderMaster implements Serializable {
    private List<OrderDetail> orderDetailList;
}

