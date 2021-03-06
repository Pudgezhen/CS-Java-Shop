package com.wz.service.goods.controller;

import com.wz.api.goods.model.Category;
import com.wz.mall.util.RespResult;
import com.wz.service.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/parent/{id}")
    public RespResult<List<Category>> findByParentId(@PathVariable Integer id){

        return RespResult.ok(categoryService.FindByParenetId(id));
    }

    /**
     * 根据分类id查询分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RespResult<Category> one(@PathVariable("id") Integer id){
        Category byId = categoryService.getById(id);
        return RespResult.ok(byId);
    }



}
