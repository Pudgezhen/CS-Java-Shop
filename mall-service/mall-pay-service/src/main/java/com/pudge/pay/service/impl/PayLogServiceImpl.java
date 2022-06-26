package com.pudge.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pudge.mall.pay.model.PayLog;
import com.pudge.pay.mapper.PayLogMapper;
import com.pudge.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {


    @Autowired
    private PayLogMapper payLogMapper;
    /**
     * 记录支付日志
     * @param payLog
     */
    @Override
    public void add(PayLog payLog) {
        payLogMapper.deleteById(payLog.getId());

        payLogMapper.insert(payLog);
    }
}
