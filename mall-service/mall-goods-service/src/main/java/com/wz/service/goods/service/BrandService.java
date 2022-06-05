package com.wz.service.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.goods.model.Brand;

import java.util.List;


public interface BrandService extends IService<Brand> {

    List<Brand> queryList(Brand brand);

    Page<Brand> queryPage(Brand brand,Long currentPage,Long size);


    /**
     * 根据分类Id查询品牌集合
     */
    List<Brand> queryByCategoryId(Integer id);
}
