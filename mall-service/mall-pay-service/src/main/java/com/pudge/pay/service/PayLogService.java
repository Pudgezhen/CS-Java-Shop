package com.pudge.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pudge.mall.pay.model.PayLog;

public interface PayLogService extends IService<PayLog> {

    void add(PayLog payLog);
}
