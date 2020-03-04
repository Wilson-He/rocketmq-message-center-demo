package io.wilson.message.service;

import io.wilson.common.message.SmsMessage;
import org.springframework.stereotype.Service;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Service
public interface SmsMessageService extends BaseMessageService<SmsMessage>{

    void send(SmsMessage smsMessage);

}
