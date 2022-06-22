package com.wz.service.cart.service;

import com.wz.api.cart.model.Cart;

import java.util.List;

public interface CartService {

    void add(String id,String userName, Integer num);

    List<Cart> list(String username);

    List<Cart> list(List<String> ids);

    void delete(List<String> ids);
}
