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
     * 发送单条短信消息
     *
     * @param smsMessage
     * @return 业务处理结果
     */
    boolean sendSingle(SmsMessage smsMessage);

}
