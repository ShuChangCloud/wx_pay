package com.xmcc.service.impl;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xmcc.common.OrderEnums;
import com.xmcc.common.PayEnums;
import com.xmcc.common.ResultEnums;
import com.xmcc.dto.OrderDetailDto;
import com.xmcc.dto.OrderMasterDto;
import com.xmcc.dto.OrderMasterDto1;
import com.xmcc.dto.PageDto;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.entity.ProductInfo;
import com.xmcc.exception.CustomException;
import com.xmcc.repository.OrderDetailRepository;
import com.xmcc.repository.OrderMasterRepository;
import com.xmcc.service.OrderDetailService;
import com.xmcc.service.OrderMasterService;
import com.xmcc.service.ProductInfoService;
import com.xmcc.util.BigDecimalUtil;
import com.xmcc.util.IDUtils;
import com.xmcc.util.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-21 20:23
 */

@Service
@Transactional
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResultResponse insertOrder(OrderMasterDto masterDto) {
        //获取所有订单项items
        List<OrderDetailDto> items = masterDto.getItems();

        //订单项列表orderDetailList
        ArrayList<OrderDetail> orderDetailList = Lists.newArrayList();

        //订单内的商品总价totalPrice
        BigDecimal totalPrice= BigDecimal.valueOf(0);

        //对订单项列表遍历,根据订单中每项商品来计算总价
        for (OrderDetailDto orderDetailDto : items) {
            //得到每项订单的id,再根据id得到每项商品的信息productPro
            String productId = orderDetailDto.getProductId();
            ResultResponse<ProductInfo> productPro = productInfoService.queryById(productId);

            //判断各项商品的状态(ResultResponse是否里面真的有数据)
            if (productPro.getCode()== ResultEnums.FAIL.getCode()) {
                throw  new CustomException(productPro.getMsg());
            }

            ProductInfo productInfo = productPro.getData();
            //判断商品库存是否充足
            if (productInfo.getProductStock()<orderDetailDto.getProductQuantity()) {
                throw new CustomException(ResultEnums.PRODUCT_NOT_ENOUGH.getMsg());
            }
            //创建订单项
            OrderDetail detail = OrderDetail.builder().detailId(IDUtils.createIdbyUUID())
                    .productIcon(productInfo.getProductIcon())
                    .productId(orderDetailDto.getProductId())
                    .productName(productInfo.getProductName())
                    .productPrice(productInfo.getProductPrice())
                    .productQuantity(orderDetailDto.getProductQuantity())
                    .build();

            orderDetailList.add(detail);

            //修改商品信息(卖出去的商品要减少库存)
            productInfo.setProductStock(productInfo.getProductStock()-orderDetailDto.getProductQuantity());
            productInfoService.updateProductInfo(productInfo);

            //计算订单总价
            totalPrice= BigDecimalUtil.add(totalPrice,
                    BigDecimalUtil.multi(productInfo.getProductPrice(), orderDetailDto.getProductQuantity()));

        }
        //----构建订单信息-----
        //生成订单id
        String uuid = IDUtils.createIdbyUUID();

        OrderMaster master = OrderMaster.builder().orderId(uuid)
                .buyerAddress(masterDto.getAddress())
                .buyerName(masterDto.getName())
                .buyerOpenid(masterDto.getOpenid())
                .buyerPhone(masterDto.getPhone())
                .createTime(new Date())
                .orderStatus(OrderEnums.NEW.getCode())
                .payStatus(PayEnums.WAIT.getCode())
                .orderAmount(totalPrice)
                .updateTime(new Date()).build();
        //修改所有订单项的订单id
        List<OrderDetail> details = orderDetailList.stream().map(getOrderDetailOrderDetailFunction(uuid)).collect(toList());
        //批量插入订单到数据库
        orderDetailService.batchInsert(details);
        orderMasterRepository.save(master);

        HashMap<String, String> map = Maps.newHashMap();
        map.put("orderId",master.getOrderId());
        return ResultResponse.success(map);
    }

    @Override
    public List<OrderMaster> findOrderByOpenidPageable(PageDto pageDto) {
        Pageable pageable = new PageRequest(pageDto.getPage(),pageDto.getSize());
        Page<OrderMaster> masterPage = orderMasterRepository.findOrderMasterByBuyerOpenidPageable(pageDto.getOpenid(), pageable);
        List<OrderMaster> masterList = masterPage.getContent();
        return masterList;
    }

    /**
     * 通过openID和orderid去查询订单
     * @param openid
     * @param orderId
     * @return 订单
     */
    @Override
    public OrderMasterDto1 findOrderDetail(String openid, String orderId) {
        //通过openid和orderId查找order_detailid的列表
        //通过detailid列表查询所有的order detail
        OrderMaster orderMaster = orderMasterRepository.findOrderMasterByBuyerOpenidAndOrderId(openid, orderId);
        List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailByOrderId(orderId);
        OrderMasterDto1 dto = new OrderMasterDto1();
        BeanUtils.copyProperties(orderMaster, dto);
        dto.setOrderDetailList(orderDetailList);
        return dto;
    }

    /**
     * 取消订单
     * @param openid 用户openID
     * @param orderId 订单id
     * @return
     */
    @Override
    @Transactional
    public boolean cancelOrder(String openid, String orderId) {
        //根据orderID查询订单是否存在
        //查询订单状态 -订单状态不为0的不可取消订单
        //将订单的状态改为取消
        //根据订单号查询订单详情列表，将订单详情里的商品已下单的商品数量 返回给数据库
        if (StringUtils.isEmpty(orderId)&&StringUtils.isEmpty(openid)) {
            throw new CustomException(OrderEnums.OPENID_ERROR.getMsg()+"或orderId参数异常");
        }
        if (orderMasterRepository.countByOrderId(orderId)<1) {
            throw new CustomException(OrderEnums.ORDER_NOT_EXITS.getMsg());
        }
        //获取订单
        OrderMaster orderMaster = orderMasterRepository.findOrderMasterByBuyerOpenidAndOrderId(openid, orderId);
        if (orderMaster.getOrderStatus()!=0) {
            throw new CustomException("订单状态异常:"+orderMaster.getOrderStatus());
        }

        //TODO:将订单详情里的商品已下单的商品数量 返回给数据库
        orderMasterRepository.updateOrOrderStatus(OrderEnums.CANCEL.getCode(),orderId);
        return true;
    }

    private Function<OrderDetail, OrderDetail> getOrderDetailOrderDetailFunction(String uuid) {
        return orderDetail -> {
            orderDetail.setOrderId(uuid);
            return orderDetail; };
    }
}
