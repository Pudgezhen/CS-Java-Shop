package com.wz.api.goods.feign;

import com.wz.api.goods.model.Category;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-goods")
public interface CategoryFeign {

    /**
     * 根据分类id查询分类信息
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    RespResult<Category> one(@PathVariable("id") Integer id);
}
