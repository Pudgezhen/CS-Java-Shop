package com.wz.service.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wz.api.goods.model.Sku;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuMapper extends BaseMapper<Sku> {

    @Update("UPDATE sku set num=num-#{num} where id=#{id} and num>=#{num}")
    int dcount(@Param("id")String id, @Param("num")Integer num);
}
