package com.xmcc.repository;

import com.xmcc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 17:26
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findOrderDetailByOrderId(String orderId);

}
