package com.wz.service.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wz.api.goods.model.Brand;
import com.wz.mall.util.RespResult;
import com.wz.service.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;
    /**
     * 增加方法
     */
    @PostMapping("/add")
    public RespResult add(@RequestBody Brand brand){
        brandService.save(brand);
        return RespResult.ok();
    }

    /**
     * 修改方法
     */
    @PutMapping("/update")
    public RespResult update(@RequestBody Brand brand){
        brandService.updateById(brand);
        return RespResult.ok();
    }

    /**
     * 删除方法
     */
    @DeleteMapping("/delete/{id}")
    public RespResult delete(@PathVariable("id") int id){
        brandService.removeById(id);
        return RespResult.ok();
    }
    /**
     * 条件查询
     */
    @PostMapping("queryList")
    public RespResult<List<Brand>> query(@RequestBody Brand brand){
        List<Brand> brands = brandService.queryList(brand);
        return RespResult.ok(brands);
    }

    /**
     * 分页查询
     */
    @PostMapping("queryPage/{page}/{size}")
    public RespResult<Page<Brand>> queryPage(@RequestBody Brand brand,
                                             @PathVariable("page")Long page,
                                             @PathVariable("size")Long size){
        Page<Brand> pageInfos = brandService.queryPage(brand,page,size);
        return RespResult.ok(pageInfos);
    }

    /**
     * 根据分类id查询品牌集合
     */
    @GetMapping("/category/{pid}")
    public RespResult<List<Brand>> categoryBrands(@PathVariable("pid") Integer pid ){
        return RespResult.ok(brandService.queryByCategoryId(pid));
    }

}
