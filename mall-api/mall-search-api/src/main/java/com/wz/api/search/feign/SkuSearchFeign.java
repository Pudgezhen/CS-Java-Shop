package com.wz.api.search.feign;

import com.wz.api.search.model.SkuES;
import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mall-search")
@RequestMapping("/search")
public interface SkuSearchFeign {

    @PostMapping("/add")
    RespResult save(@RequestBody SkuES skuES);

    /**
     * 删除索引
     */
    @PostMapping("/del/{id}")
    RespResult del(@PathVariable("id") String id);
}
