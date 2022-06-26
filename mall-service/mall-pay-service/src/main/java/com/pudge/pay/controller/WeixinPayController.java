package com.pudge.pay.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.pudge.mall.pay.model.PayLog;
import com.pudge.pay.service.WeiXinPayService;
import com.wz.mall.util.RespResult;
import com.wz.mall.util.Signature;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class WeixinPayController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    private Signature signature;

    @GetMapping("/result")
    public RespResult result(HttpServletRequest request) throws Exception {
        //读取网络输入流
        ServletInputStream is = request.getInputStream();
        //定义接收输入流对象
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //将网络输入流读取到输出流中
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=is.read(buffer))!=-1){
            os.write(buffer,0,len);
        }
        //关闭资源
        os.close();
        is.close();
        //将支付结果的XML结构转换为Map结构
        String xmlResult = new String(os.toByteArray(),"UTF-8");
        Map<String,String> map = WXPayUtil.xmlToMap(xmlResult);
        //判断支付结果状态 日志状态 2为成功，7为失败
        int status = 7;
        if (map.get("return_code").equals(WXPayConstants.SUCCESS) && map.get("result_code").equals(WXPayConstants.SUCCESS)){
            status = 2;
        }


        //创建日志对象
        PayLog payLog = new PayLog(map.get("out_trade_no"),status,JSON.toJSONString(map),map.get("out_trade_no"),new Date());

        //构建消息
        Message<String> message = MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();

        //发消息
        rocketMQTemplate.sendMessageInTransaction("rocket","log",message,null);
        return RespResult.ok();
    }

    /**
     * 微信支付二维码获取
     */
    @GetMapping("/pay")
    public RespResult<Map> pay(@RequestParam("ciptext") String ciphertext) throws Exception {

        Map<String,String> dataMap =  signature.security(ciphertext);

        Map<String,String> map = weiXinPayService.preOrder(dataMap);
        if (map!=null){
            map.put("orderNumber",dataMap.get("out_trade_no"));
            map.put("money",dataMap.get("total_fee"));
            return RespResult.ok(map);
        }
        return RespResult.error("支付系统繁忙，稍后再试");
    }

}
