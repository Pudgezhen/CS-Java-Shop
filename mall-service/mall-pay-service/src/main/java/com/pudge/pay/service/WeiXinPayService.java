package com.pudge.pay.service;

import java.util.Map;

public interface WeiXinPayService {
    // 预支付订单创建--获取支付二维码
    Map<String,String> preOrder(Map<String,String> dataMap) throws Exception;
}
