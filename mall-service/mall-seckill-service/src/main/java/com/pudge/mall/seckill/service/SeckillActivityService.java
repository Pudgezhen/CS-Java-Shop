package com.pudge.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wz.api.seckill.model.SeckillActivity;
import java.util.List;

public interface SeckillActivityService extends IService<SeckillActivity>{

    /**
     * 有效活动列表
     */
    List<SeckillActivity> validActivity();

}
