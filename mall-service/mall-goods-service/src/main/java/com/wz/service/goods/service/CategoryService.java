package com.wz.service.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.goods.model.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> FindByParenetId(Integer id);
}
