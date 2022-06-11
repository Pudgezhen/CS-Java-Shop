package com.wz.service.goods.controller;

import com.wz.api.goods.model.Product;
import com.wz.api.goods.model.Spu;
import com.wz.mall.util.RespResult;
import com.wz.service.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;


    /**
     * 产品保存
     * @param product
     * @return
     */
    @PostMapping("/save")
    public RespResult saveProduct(@RequestBody Product product){
        spuService.saveProduct(product);
        return RespResult.ok();
    }

    /**
     * 根据id查询product
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public RespResult<Product> one(@PathVariable("id") String id){
        Product product = spuService.findBySpuId(id);
        return RespResult.ok(product);
    }


}
