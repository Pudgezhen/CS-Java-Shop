package com.pudge.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.order.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
