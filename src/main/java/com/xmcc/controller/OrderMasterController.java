package com.xmcc.controller;

import com.google.common.collect.Maps;
import com.xmcc.dto.OrderMasterDto;
import com.xmcc.service.OrderMasterService;
import com.xmcc.util.JsonUtil;
import com.xmcc.util.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @company xmcc
 * @create create by qcc on 2019-06-22 10:45
 */

@RestController
@RequestMapping("buyer/order")
@Api(description = "完成订单的增删改查",value ="订单相关的接口" )
public class OrderMasterController {

    @Autowired
    private OrderMasterService orderMasterService;

    @RequestMapping("create")
    @ApiOperation(value = "创建订单",httpMethod = "POST",response = ResultResponse.class)
    public ResultResponse create(@Valid @ApiParam(value = "传入json格式",name = "订单对象",required = true) OrderMasterDto masterDto,
                                 BindingResult bindingResult){
        Map<String,String> map = Maps.newHashMap();
        //判断是否有参数校验问题
        if(bindingResult.hasErrors()){
            List<String> errList = bindingResult.getFieldErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
            map.put("参数校验错误", JsonUtil.object2string(errList));
           //将参数校验的错误信息返回给前台
            return  ResultResponse.fail(map);
       }
        return orderMasterService.insertOrder(masterDto);

    }
}
