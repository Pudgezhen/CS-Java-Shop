package com.pudge.pay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.pudge.pay.service.WeiXinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeiXinPayServiceImpl implements WeiXinPayService {

    @Autowired
    private WXPay wxPay;

    @Override
    public Map<String, String> preOrder(Map<String, String> dataMap) throws Exception {
        return wxPay.unifiedOrder(dataMap);
    }
}
