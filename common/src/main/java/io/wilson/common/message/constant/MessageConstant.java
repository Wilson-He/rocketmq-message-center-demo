package io.wilson.common.message.constant;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
public interface MessageConstant {
    interface System {
        String PRODUCT = "PRODUCT";
    }

    interface Topic{
        String SMS_TOPIC = "${rocketmq.topic.sms}";
        String MAIL_TOPIC = "${rocketmq.mail.sms}";
    }
    interface Producer{
        String SMS_GROUP = "${rocketmq.producer.group.sms}";
        String MAIL_GROUP = "${rocketmq.producer.group.mail}";
    }

    interface Consumer {
        String SMS_GROUP = "${rocketmq.consumer.group.sms}";
        String MAIL_GROUP = "${rocketmq.consumer.group.mail}";
    }
}
