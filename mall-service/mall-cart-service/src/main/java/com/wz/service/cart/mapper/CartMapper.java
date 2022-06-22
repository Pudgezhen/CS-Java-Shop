package com.wz.service.cart.mapper;

import com.wz.api.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartMapper extends MongoRepository<Cart,String> {
}
