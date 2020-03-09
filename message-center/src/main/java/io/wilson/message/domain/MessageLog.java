package io.wilson.message.domain;

import io.wilson.common.message.BaseMessage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息记录
 *
 * @author Wilson
 * @since 2020/3/4
 **/
@Data
@Accessors(chain = true)
public class MessageLog implements Serializable {
    private String msgId;
    /**
     * 发送方系统名称 {@link io.wilson.common.message.constant.MessageConstant}
     */
    private String system;
    /**
     * 消息对象json字符串
     */
    private String msgContent;
    /**
     * 业务执行结果
     */
    private Boolean success;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 初始化消息记录
     *
     * @param message       消息
     * @return
     */
    public static <T extends BaseMessage> MessageLog convertFromMessage(T message) {
        LocalDateTime now = LocalDateTime.now();
        return new MessageLog()
                .setSystem(message.getSystem())
                .setSuccess(false)
                .setCreateTime(now)
                .setUpdateTime(now);
    }
}
