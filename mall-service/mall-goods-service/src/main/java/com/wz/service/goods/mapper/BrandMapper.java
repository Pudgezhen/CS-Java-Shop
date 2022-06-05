package com.wz.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.api.goods.model.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 1.根据分类id查询品牌集合
     * 2.根据品牌id集合查询每个品牌的信息
     */
    @Select("SELECT brand_id FROM category_brand WHERE category_id = #{id}")
    List<Integer> queryBrandIds(Integer id);
}
