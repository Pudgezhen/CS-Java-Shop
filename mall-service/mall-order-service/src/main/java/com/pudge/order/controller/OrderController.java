package com.pudge.order.controller;

import com.pudge.order.service.OrderService;
import com.wz.mall.util.RespCode;
import com.wz.mall.util.RespResult;
import com.wz.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public RespResult add(@RequestBody Order order){
        order.setUsername("wz");
        Boolean bo = orderService.add(order);
        return bo? RespResult.ok() : RespResult.error(RespCode.ERROR);
    }

}
