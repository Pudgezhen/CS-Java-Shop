package com.pudge.mall.page.controller;


import com.pudge.mall.page.service.PageService;
import com.wz.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public RespResult html(@PathVariable("id") String id)throws Exception{
        pageService.html(id);
        return RespResult.ok();
    }

}
