package com.pudge.mall.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.pudge.mall.page.service.PageService;
import com.wz.api.goods.feign.CategoryFeign;
import com.wz.api.goods.feign.SpuFeign;
import com.wz.api.goods.model.Category;
import com.wz.api.goods.model.Product;
import com.wz.api.goods.model.Sku;
import com.wz.api.goods.model.Spu;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Value("${pagepath}")
    private String pagepath;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成静态页
     * @param spuId
     * @throws Exception
     */
    @Override
    public void html(String spuId) throws Exception {
        //1.创建容器对象（上下文对象）
        Context context = new Context();
        //2.设置模板数据
        context.setVariables(loadData(spuId));
        //3.指定文件生成后存储路径
        File file = new File(pagepath,spuId+".html");
        PrintWriter printWriter = new PrintWriter(file,"UTF-8");
        //4.执行合成生成
        templateEngine.process("item",context,printWriter);
    }


    /**
     * 数据加载
     */
    public Map<String,Object> loadData(String spuid){
        //1.查询数据
        RespResult<Product> productRespResult = spuFeign.one(spuid);
        Product product = productRespResult.getData();
        if (product != null){
            //Map
            Map<String,Object> resultMap = new HashMap<>();
            //Spu
            Spu spu = product.getSpu();
            resultMap.put("spu",spu);
            //图片处理
            resultMap.put("images",spu.getImages().split(","));
            //属性列表
            resultMap.put("attrs", JSON.parseObject(spu.getAttributeList(),Map.class));
            //三级分类
            RespResult<Category> one = categoryFeign.one(spu.getCategoryOneId());
            RespResult<Category> two = categoryFeign.one(spu.getCategoryTwoId());
            RespResult<Category> three = categoryFeign.one(spu.getCategoryThreeId());
            resultMap.put("one",one.getData());
            resultMap.put("two",two.getData());
            resultMap.put("three",three.getData());

            //Sku集合
            List<Map<String,Object>> skuList = new ArrayList<>();
            for (Sku sku : product.getSkus()) {
                Map<String,Object> skuMap = new HashMap<>();
                skuMap.put("id",sku.getId());
                skuMap.put("name",sku.getName());
                skuMap.put("price",sku.getPrice());
                skuMap.put("attr",sku.getSkuAttribute());
                skuList.add(skuMap);
            }
            resultMap.put("skuList",skuList);
            return resultMap;
        }
        return null;
    }

}
