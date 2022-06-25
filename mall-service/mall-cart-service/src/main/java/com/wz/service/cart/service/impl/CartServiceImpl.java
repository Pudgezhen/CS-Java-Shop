package com.wz.service.cart.service.impl;

import com.google.common.collect.Lists;
import com.wz.api.cart.model.Cart;
import com.wz.api.goods.feign.SkuFeign;
import com.wz.api.goods.model.Sku;
import com.wz.mall.util.RespResult;
import com.wz.service.cart.mapper.CartMapper;
import com.wz.service.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(String id, String userName, Integer num) {
        //ID不能冲突

        //删除之前的重复商品的购物车记录
        cartMapper.deleteById(userName+id);
        if (num >0 ) {
            //根据ID查询SKU详情
            RespResult<Sku> Resku = skuFeign.one(id);
            //将当前商品加入购物车
            Sku sku = Resku.getData();
            Cart cart = new Cart(userName + id, userName, sku.getName(), sku.getPrice(), sku.getImage(), id, num);
            cartMapper.save(cart);
        }
    }

    @Override
    public List<Cart> list(String username) {
        Cart cart = new Cart();
        cart.setUserName(username);
        return cartMapper.findAll(Example.of(cart), Sort.by("_id"));
    }

    @Override
    public List<Cart> list(List<String> ids) {
        if (ids != null){
            Iterable<Cart> carts = cartMapper.findAllById(ids);
            return Lists.newArrayList(carts);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<String> ids) {
        mongoTemplate.remove(Query.query(Criteria.where("_id").in(ids)),Cart.class);
    }
}
