package io.wilson.message.listener;

import com.alibaba.fastjson.JSONObject;
import io.wilson.common.message.SmsMessage;
import io.wilson.common.message.constant.MessageConstant;
import io.wilson.message.domain.constant.MessageLogConstant;
import io.wilson.message.exception.MQConsumeException;
import io.wilson.message.service.SmsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Slf4j
@Service
@ConditionalOnProperty("rocketmq.topic.sms")
@RocketMQMessageListener(topic = MessageConstant.Topic.SMS_TOPIC_TEMPLATE, consumerGroup = MessageConstant.Consumer.SMS_GROUP_TEMPLATE)
public class SmsListener extends AbstractListener<SmsMessage> implements RocketMQListener<MessageExt> {
    @Resource
    private SmsMessageService smsMessageService;
    private static final String EXCEPTION_FORMAT = "短信消息消费失败，消息内容：{}";

    @Override
    public void onMessage(MessageExt message) {
        if(isConsumed(message.getMsgId())){
            return;
        }
        String jsonBody = new String(message.getBody());
        SmsMessage smsMessage = JSONObject.parseObject(jsonBody, SmsMessage.class);
        smsMessage.setMsgId(message.getMsgId());
        if (smsMessageService.consume(smsMessage, smsMessageService::send)) {
            log.info("短信消息{}消费成功", message.getMsgId());
        } else {
            throw new MQConsumeException(EXCEPTION_FORMAT);
        }
    }

    @Override
    protected String collection() {
        return MessageLogConstant.CollectionName.SMS;
    }
}
