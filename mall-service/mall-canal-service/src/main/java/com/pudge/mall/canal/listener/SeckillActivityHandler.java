package com.pudge.mall.canal.listener;



import com.pudge.mall.canal.job.dynamic.DynamicJob;
import com.pudge.mall.canal.job.dynamic.DynamicTaskCreate;
import com.wz.api.seckill.model.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.text.SimpleDateFormat;

@CanalTable("seckill_activity")
@Component
public class SeckillActivityHandler implements EntryHandler<SeckillActivity> {

    @Autowired
    private DynamicTaskCreate dynamicTaskCreate;

    @Override
    public void insert(SeckillActivity seckillActivity) {
        //创建任务调度，活动结束时执行
        String cron = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        cron = sdf.format(seckillActivity.getEndTime());
        System.out.println("执行时间：" + cron);
        dynamicTaskCreate.create(seckillActivity.getId(),cron,1,new DynamicJob(),seckillActivity.getId());
    }

    @Override
    public void update(SeckillActivity before, SeckillActivity after) {

    }

    @Override
    public void delete(SeckillActivity seckillActivity) {

    }
}
