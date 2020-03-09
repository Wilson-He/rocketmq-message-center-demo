package io.wilson.message.service.impl;

import io.wilson.common.message.SmsMessage;
import io.wilson.message.service.SmsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 短信消息业务实现类
 *
 * @author Wilson
 * @since 2020/3/2
 **/
@Service
@Slf4j
public class SmsMessageServiceImpl implements SmsMessageService {

    @Override
    public boolean sendSingle(SmsMessage smsMessage) {
        // 短信业务操作结果
        boolean isSuccess = true;
        /*
         * 短信业务操作并把操作结果设到isSuccess中
         */
        if (Objects.equals(smsMessage.getToUserId(), "Wilson")) {
            isSuccess = false;
            log.info("短信发送失败,消息内容:{}", smsMessage);
        }
        return isSuccess;
    }
}
