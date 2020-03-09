package io.wilson.message.listener;

import com.alibaba.fastjson.JSONObject;
import io.wilson.common.message.SmsMessage;
import io.wilson.common.message.constant.MessageConstant;
import io.wilson.message.domain.constant.MessageLogConstant;
import io.wilson.message.service.SmsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信消息监听器(消费者)
 *
 * @author Wilson
 * @since 2020/3/2
 **/
@Slf4j
@Service
@ConditionalOnProperty(MessageConstant.Topic.SMS_TOPIC)
@RocketMQMessageListener(topic = MessageConstant.Topic.SMS_TOPIC_TEMPLATE, consumerGroup = MessageConstant.Consumer.SMS_GROUP_TEMPLATE)
public class SmsMessageListener extends AbstractMQStoreListener implements RocketMQListener<MessageExt> {
    @Resource
    private SmsMessageService smsMessageService;
    private static final String EXCEPTION_FORMAT = "短信消息消费失败，消息内容：%s";

    @Override
    public void onMessage(MessageExt message) {
        String msgId = message.getMsgId();
        if (isConsumed(msgId)) {
            return;
        }
        SmsMessage smsMessage = JSONObject.parseObject(message.getBody(), SmsMessage.class);
        log.info("接收到短信消息{}：{}", msgId, smsMessage);
        /*if (Objects.equals(smsMessage.getToUserId(), "2020")) {
            log.error("消息{}消费失败", message.getMsgId());
            // 抛出异常让RocketMQ重新投递消息重新消费
            throw new MQConsumeException(String.format(EXCEPTION_FORMAT, smsMessage));
        }*/
        boolean isSuccess = smsMessageService.consume(smsMessage, smsMessageService::sendSingle);
        if (!isSuccess) {
            log.info("短信消息业务操作失败,消息id: {}", msgId);
        }
        // 保存消息消费记录
        store(isSuccess, msgId, smsMessage);
    }

    @Override
    String collection() {
        return MessageLogConstant.CollectionName.SMS;
    }
}