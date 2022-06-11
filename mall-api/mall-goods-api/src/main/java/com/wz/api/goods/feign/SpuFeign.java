package com.wz.api.goods.feign;

import com.wz.api.goods.model.Product;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-goods")
public interface SpuFeign {


    /**
     * 根据id查询product
     * @param id
     * @return
     */
    @GetMapping("/spu/product/{id}")
    RespResult<Product> one(@PathVariable("id") String id);
}
