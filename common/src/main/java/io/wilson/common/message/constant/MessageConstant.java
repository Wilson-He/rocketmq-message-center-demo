package io.wilson.common.message.constant;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
public interface MessageConstant {

    interface System {
        String PRODUCT = "PRODUCT";
        String QUESTION = "QUESTION";
    }

    interface Topic {
        String SMS_TOPIC = "rocketmq.topic.sms";
        String SMS_TOPIC_TEMPLATE = "${rocketmq.topic.sms}";
        String MAIL_TOPIC = "rocketmq.topic.mail";
        String MAIL_TOPIC_TEMPLATE = "${rocketmq.topic.mail}";
    }

    interface Producer {
        String SMS_GROUP_TEMPLATE = "${rocketmq.producer.group.sms}";
        String MAIL_GROUP_TEMPLATE = "${rocketmq.producer.group.mail}";
    }

    interface Consumer {
        String SMS_GROUP_TEMPLATE = "${rocketmq.consumer.group.sms}";
        String MAIL_GROUP_TEMPLATE = "${rocketmq.consumer.group.mail}";
    }
}
