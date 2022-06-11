package com.pudge.mall.page.feign;

import com.wz.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("mall-web-page")
public interface PageFeign {

    /**
     * 生成静态页
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/page/{id}")
    RespResult html(@PathVariable("id") String id)throws Exception;
}
