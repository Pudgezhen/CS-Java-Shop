package com.wz.api.cart.feign;

import com.wz.api.cart.model.Cart;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("mall-cart")
@RequestMapping("/cart")
public interface CartFeign {
    @PostMapping("/list")
    RespResult<List<Cart>> list(@RequestBody List<String> ids);

    @DeleteMapping
    RespResult delete(@RequestBody List<String> ids);
}
