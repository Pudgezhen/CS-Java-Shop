package com.pudge.order.controller;

import com.pudge.order.pay.WeixinPayParam;
import com.pudge.order.service.OrderService;
import com.wz.mall.util.RespCode;
import com.wz.mall.util.RespResult;
import com.wz.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeixinPayParam weixinPayParam;

    @PostMapping
    public RespResult add(@RequestBody Order order, HttpServletRequest request) throws Exception {
        order.setUsername("wz");
        Boolean bo = orderService.add(order);
        String ciptxt = weixinPayParam.weixinParam(order,request);
        return bo? RespResult.ok(ciptxt) : RespResult.error(RespCode.ERROR);
    }

}
