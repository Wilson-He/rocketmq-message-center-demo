package io.wilson.message.listener;

import io.wilson.common.message.constant.MessageConstant;
import io.wilson.common.message.SmsMessage;
import io.wilson.message.service.SmsMessageService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Component
@RocketMQMessageListener(topic = MessageConstant.Topic.SMS_TOPIC, consumerGroup = MessageConstant.Consumer.SMS_GROUP)
public class SmsListener implements RocketMQListener<SmsMessage> {
    @Resource
    private SmsMessageService smsMessageService;

    @Override
    public void onMessage(SmsMessage message) {
        smsMessageService.consume(message, smsMessageService::send);
    }
}
