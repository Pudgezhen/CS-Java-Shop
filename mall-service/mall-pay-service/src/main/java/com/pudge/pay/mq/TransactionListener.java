package com.pudge.pay.mq;

import com.alibaba.fastjson.JSON;
import com.pudge.mall.pay.model.PayLog;
import com.pudge.pay.service.PayLogService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RocketMQTransactionListener(txProducerGroup = "rocket")
public class TransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private PayLogService payLogService;
    /**
     * 当向RocketMQ的Broker发送half消息成功后，调用该方法
     * @param message  发送的消息，额外的参数
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            String result = new String((byte[]) message.getPayload(),"UTF-8");
            PayLog payLog = JSON.parseObject(result,PayLog.class);
            payLogService.add(payLog);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 超时回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
