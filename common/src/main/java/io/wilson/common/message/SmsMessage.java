package io.wilson.common.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class SmsMessage extends BaseMessage {
    private static final String COLLECTION = "sms_message";

    /**
     * 消息源系统:{@link io.wilson.common.message.constant.MessageConstant.System}
     */
    private String system;
    /**
     * 短信创建用户
     */
    private String createUserId;
    /**
     * 接收短信用户
     */
    private String toUserId;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 短信内容
     */
    private String content;

}
