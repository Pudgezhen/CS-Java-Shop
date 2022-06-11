package com.pudge.mall.search.controller;


import com.wz.api.search.feign.SkuSearchFeign;
import com.wz.mall.util.RespResult;
import com.wz.mall.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/web/search")
public class SearchController {

    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @GetMapping()
    public String search(@RequestParam(required = false) Map<String,Object> searchMap, Model model){
        RespResult<Map<String, Object>> result = skuSearchFeign.search(searchMap);

        //组装用户访问的url
        model.addAttribute("url", UrlUtils.map2url("/web/search",searchMap,"page"));
        model.addAttribute("sorturl", UrlUtils.map2url("/web/search",searchMap,"page","sm","sfield"));
        model.addAttribute("result",result.getData());
        model.addAttribute("searchMap",searchMap);
        return "search";
    }





}
