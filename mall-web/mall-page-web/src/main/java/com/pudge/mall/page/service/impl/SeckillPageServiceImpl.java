package com.pudge.mall.page.service.impl;

import com.pudge.mall.page.service.SeckillPageService;
import com.wz.api.seckill.feign.SeckillGoodsFeign;
import com.wz.api.seckill.model.SeckillGoods;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillPageServiceImpl implements SeckillPageService {


    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;

    @Value("${seckillpath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成秒杀静态页
     * @param id
     * @throws Exception
     */
    @Override
    public void html(String id) throws Exception {
        //1.创建容器对象（上下文对象）
        Context context = new Context();
        //2.设置模板数据
        context.setVariables(loadData(id));
        //3.指定文件生成后存储路径
        File file = new File(pagepath,id+".html");
        PrintWriter printWriter = new PrintWriter(file,"UTF-8");
        //4.执行合成生成
        templateEngine.process("seckillitem",context,printWriter);
    }

    @Override
    public void delete(String id) {
        //创建要删除的文件
        System.out.println("删除文件的id"+id);
        File file = new File(pagepath,id+".html");

        if (file.exists()){
            file.delete();
        }
    }


    /**
     * 数据加载
     */
    public Map<String,Object> loadData(String id){
        //1.查询数据
        RespResult<SeckillGoods> goodsResp = seckillGoodsFeign.one(id);
        if (goodsResp.getData() != null){
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("item",goodsResp.getData());
            dataMap.put("images",goodsResp.getData().getImages().split(","));
            return dataMap;
        }
        return null;
    }

}
