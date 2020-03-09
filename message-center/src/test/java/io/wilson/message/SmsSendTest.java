package io.wilson.message;

import io.wilson.common.message.SmsMessage;
import io.wilson.common.message.constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/4
 **/
@SpringBootTest(classes = MessageCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SmsSendTest {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Value(MessageConstant.Topic.SMS_TOPIC_TEMPLATE)
    private String smsTopic;

    @Test
    public void sendSms() {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setToUserId("13211")
                .setMobile("173333222")
                .setContent("测试短信消息")
                .setSystem(MessageConstant.System.QUESTION);
        SendResult sendResult = rocketMQTemplate.syncSend(smsTopic, MessageBuilder.withPayload(smsMessage).build());
        log.info("send msg id: {}", sendResult.getMsgId());
    }
}
