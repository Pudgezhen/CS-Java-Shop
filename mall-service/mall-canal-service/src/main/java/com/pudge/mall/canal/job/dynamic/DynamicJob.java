package com.pudge.mall.canal.job.dynamic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.pudge.mall.canal.spring.SpringContext;
import com.pudge.mall.page.feign.SeckillPageFeign;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1.执行周期
 * 2.分片
 * 3.指定Zookeeper中的命名空间
 */

public class DynamicJob implements SimpleJob {

    //执行的作业
    @Override
    public void execute(ShardingContext shardingContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("DynamicJobTask----execute-----动态定时任务:"+ shardingContext.getJobParameter() +sdf.format(new Date()));
        delete(shardingContext.getJobParameter());
    }

    /**
     * 执行静态页删除
     */
    public void delete(String acid){
        //从容器中获取指定的实例
        SeckillPageFeign seckillPageFeign = SpringContext.getBean(SeckillPageFeign.class);
        seckillPageFeign.deleByAct(acid);
    }
}
