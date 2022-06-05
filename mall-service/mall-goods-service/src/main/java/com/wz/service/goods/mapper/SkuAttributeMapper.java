package com.wz.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.api.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {

    /**
     * 1.根据分类id查询属性id集合
     * 2.根据属性id查询属性集合
     */
    @Select("SELECT * FROM sku_attribute WHERE id IN (SELECT attr_id FROM category_attr WHERE category_id = #{id})")
    List<SkuAttribute> queryByCategoryId(Integer id);
}
