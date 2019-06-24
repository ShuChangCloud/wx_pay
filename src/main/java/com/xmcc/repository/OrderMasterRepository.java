package com.xmcc.repository;


import com.xmcc.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 17:27
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    @Query(value = "select o from OrderMaster o where o.buyerOpenid=:openid")
    Page<OrderMaster> findOrderMasterByBuyerOpenidPageable(@Param("openid") String openid, Pageable pageable);

    List<OrderMaster> findOrderMasterByBuyerOpenid(String openid);

    OrderMaster findOrderMasterByBuyerOpenidAndOrderId(String openid, String orderID);

    int countByOrderId(String orderid);

    @Modifying
    @Query(value = "update OrderMaster om set om.orderStatus = ?1 where om.orderId=?2")
    void updateOrOrderStatus(Integer status,String orderId);
}
