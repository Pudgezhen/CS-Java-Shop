package com.pudge.mall.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wz.api.seckill.model.SeckillActivity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    @Select("SELECT * FROM seckill_activity WHERE end_time>NoW() ORDER BY start_time ASC LIMIT 5")
    List<SeckillActivity> validActivity();
}
