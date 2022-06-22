package com.wz.api.goods.feign;

import com.wz.api.cart.model.Cart;
import com.wz.api.goods.model.Sku;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("mall-goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @GetMapping("/aditems/type")
    List<Sku> typeItems(@RequestParam(value = "id") Integer id);


    @DeleteMapping("/aditems/type")
    RespResult deltypeItems(@RequestParam(value = "id") Integer id);

    @PutMapping("/aditems/type")
    RespResult updtypeItems(@RequestParam(value = "id") Integer id);

    @GetMapping("/{id}")
    RespResult<Sku> one(@PathVariable("id") String id);

    @PostMapping("/dcount")
    RespResult dcount(@RequestBody List<Cart> carts);
}
