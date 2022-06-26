package com.pudge.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.order.mapper.OrderMapper;
import com.pudge.order.mapper.OrderSkuMapper;
import com.pudge.order.service.OrderService;
import com.wz.api.cart.feign.CartFeign;
import com.wz.api.cart.model.Cart;
import com.wz.api.goods.feign.SkuFeign;
import com.wz.mall.util.RespResult;
import com.wz.order.model.Order;
import com.wz.order.model.OrderSku;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartFeign cartFeign;

    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Autowired
    private SkuFeign skuFeign;

    @GlobalTransactional
    @Override
    public Boolean add(Order order) {
        //数据完善
        order.setId(IdWorker.getIdStr());
        order.setCreateTime(new Date());
        order.setUpdateTime(order.getCreateTime());

        //1.查询购物车数据
        RespResult<List<Cart>> cartRe = cartFeign.list(order.getCartIds());
        List<Cart> carts = cartRe.getData();
        if(carts == null || carts.size() ==0){
            return  false;
        }
        //2.递减库存
        skuFeign.dcount(carts);

        //3.添加订单明细
        int totalNum = 0;
        int moneys = 0;
        for (Cart cart : carts) {
            //将Cart转成ordersku
            OrderSku orderSku = JSON.parseObject(JSON.toJSONString(cart), OrderSku.class);
            orderSku.setId(IdWorker.getIdStr());
            orderSku.setOrderId(order.getId());
            orderSku.setMoney(orderSku.getPrice()*orderSku.getNum());

            orderSkuMapper.insert(orderSku);
            totalNum += orderSku.getNum();
            moneys += orderSku.getMoney();
        }

        //4.添加订单
        order.setTotalNum(totalNum);
        order.setMoneys(moneys);
        orderMapper.insert(order);


        //5.删除购物车
        cartFeign.delete(order.getCartIds());
        return true;
    }

    @Override
    public int updateAfterPayStatus(String id) {
        return 0;
    }
}
