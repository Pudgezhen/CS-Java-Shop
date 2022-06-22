package com.pudge.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.order.model.Order;

public interface OrderService extends IService<Order> {

    Boolean add(Order order);

}
