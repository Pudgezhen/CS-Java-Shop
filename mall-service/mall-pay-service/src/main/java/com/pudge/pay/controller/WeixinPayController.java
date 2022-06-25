package com.pudge.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.pudge.mall.pay.model.PayLog;
import com.wz.mall.util.RespResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/wx")
public class WeixinPayController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/result")
    public RespResult result(){
        PayLog payLog = new PayLog(IdWorker.getIdStr(),1,"hello","aaa",new Date());

        //构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();

        //发消息
        rocketMQTemplate.sendMessageInTransaction("rocket","log",message,null);
        return RespResult.ok();
    }

}
