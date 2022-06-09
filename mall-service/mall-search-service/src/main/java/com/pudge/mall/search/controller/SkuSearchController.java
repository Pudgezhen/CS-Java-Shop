package com.pudge.mall.search.controller;


import com.pudge.mall.search.service.SkuSearchService;
import com.wz.api.search.model.SkuES;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SkuSearchController {

    @Autowired
    private SkuSearchService skuSearchService;

    /**
     * 增加索引
     */
    @PostMapping("/add")
    public RespResult save(@RequestBody SkuES skuES){
        System.out.println("索引增加");
        skuSearchService.add(skuES);
        return RespResult.ok();
    }

    /**
     * 删除索引
     */
    @PostMapping("/del/{id}")
    public RespResult del(@PathVariable("id") String id){
        skuSearchService.del(id);
        return RespResult.ok();
    }

    /**
     * 关键词搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    public RespResult<Map<String,Object>> search(@RequestParam(required = false) Map<String,Object> searchMap ){
        Map<String, Object> search = skuSearchService.search(searchMap);
        return RespResult.ok(search);
    }

}
