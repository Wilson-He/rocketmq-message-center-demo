package io.wilson.message.service;

import io.wilson.common.message.SmsMessage;
import org.springframework.stereotype.Service;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Service
public interface SmsMessageService extends BaseMessageService<SmsMessage> {

    /**
     * 发送短信消息
     *
     * @param smsMessage
     * @return 发送结果
     */
    boolean send(SmsMessage smsMessage);

}
