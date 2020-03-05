package io.wilson.message.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.wilson.common.message.SmsMessage;
import io.wilson.message.domain.MessageLog;
import io.wilson.message.domain.constant.MessageLogConstant;
import io.wilson.message.service.AbstractMessageService;
import io.wilson.message.service.SmsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Service
@Slf4j
public class SmsMessageServiceImpl extends AbstractMessageService<SmsMessage> implements SmsMessageService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean send(SmsMessage smsMessage) {
        if (isConsumed(smsMessage)) {
            return true;
        }
        // 短信业务操作结果
        boolean isSuccess = true;
        /*
         * 短信业务操作
         * ...
         * 操作完成后进行消息记录
         */
        MessageLog messageLog = MessageLog.build(smsMessage.getMsgId(), JSONObject.toJSONString(smsMessage), smsMessage.getSystem())
                .setConsumed(isSuccess);
        // 消息发送记录
        mongoTemplate.insert(messageLog, collection());
        log.info("短信发送成功：{}", smsMessage);
        return isSuccess;
    }

    @Override
    protected String collection() {
        return MessageLogConstant.CollectionName.SMS;
    }
}
