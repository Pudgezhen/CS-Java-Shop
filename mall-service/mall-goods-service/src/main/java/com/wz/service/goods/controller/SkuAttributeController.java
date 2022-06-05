package com.wz.service.goods.controller;

import com.wz.api.goods.model.SkuAttribute;
import com.wz.mall.util.RespResult;
import com.wz.service.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skuAttribute")
@CrossOrigin
public class SkuAttributeController {

    @Autowired
    private SkuAttributeService skuAttributeService;

    @GetMapping("/category/{id}")
    public RespResult<List<SkuAttribute>> queryByCategory(@PathVariable("id") Integer id){
        return RespResult.ok(skuAttributeService.queryByCategoryId(id));
    }


}
