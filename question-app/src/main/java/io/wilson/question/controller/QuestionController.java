package io.wilson.question.controller;

import io.springframework.common.response.ServerResponse;
import io.wilson.common.message.SmsMessage;
import io.wilson.common.message.constant.MessageConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/5
 **/
@RestController
@RequestMapping("/")
public class QuestionController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value(MessageConstant.Topic.SMS_TOPIC_TEMPLATE)
    private String smsTopic;

    @GetMapping("/toUser")
    public ServerResponse sendQuestion(@RequestParam String userId) {
        rocketMQTemplate.send(smsTopic, MessageBuilder.withPayload(new SmsMessage()
                .setMobile("1833912333")
                .setContent("这是一条来自question系统的信息")
                .setToUserId(userId)
                .setSystem(MessageConstant.System.QUESTION))
                .build());
        return ServerResponse.success();
    }
}
