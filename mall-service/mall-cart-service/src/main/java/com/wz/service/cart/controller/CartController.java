package com.wz.service.cart.controller;


import com.wz.api.cart.model.Cart;
import com.wz.mall.util.RespResult;
import com.wz.service.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}/{num}")
    public RespResult add(@PathVariable("id")String id,@PathVariable("num")Integer num){
        String username = "wz";
        cartService.add(id,username,num);
        return RespResult.ok();
    }

    @GetMapping("/list")
    public RespResult list(){
        String username = "wz";
        List<Cart> cartList = cartService.list(username);
        return RespResult.ok(cartList);
    }

    @PostMapping("/list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids){
        List<Cart> list = cartService.list(ids);
        return RespResult.ok(list);
    }

    @DeleteMapping
    public RespResult delete(@RequestBody List<String> ids){
       cartService.delete(ids);
        return RespResult.ok();
    }
}
