package com.wz.service.goods.controller;



import com.wz.api.goods.model.Sku;
import com.wz.mall.util.RespResult;
import com.wz.service.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;



    @GetMapping("/aditems/type")
    public List<Sku> typeItems(@RequestParam(value = "id") Integer id){

        return skuService.typeSkuItems(id);
    }


    @DeleteMapping("/aditems/type")
    public RespResult deltypeItems(@RequestParam(value = "id") Integer id){
        skuService.deleteTypeSkuItems(id);
        return RespResult.ok();
    }

    @PutMapping("/aditems/type")
    public RespResult updtypeItems(@RequestParam(value = "id") Integer id){
        skuService.updateTypeSkuItems(id);
        return RespResult.ok();
    }
}
