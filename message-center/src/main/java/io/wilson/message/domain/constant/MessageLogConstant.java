package io.wilson.message.domain.constant;

/**
 * 维护MessageLog的相关常量(如不同消息的collection名)
 *
 * @author Wilson
 * @since 2020/3/4
 **/
public interface MessageLogConstant {

    /**
     * 各消息日志Mongo集合名
     */
    interface CollectionName {
        String SMS = "sms_message_log";
        String MAIL = "mail_message_log";
    }

}
