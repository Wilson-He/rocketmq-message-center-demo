package io.wilson.message.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 消息记录
 *
 * @author Wilson
 * @since 2020/3/4
 **/
@Data
@Accessors(chain = true)
public class MessageLog {
    private String msgId;
    /**
     * 发送方系统名称 {@link io.wilson.common.message.constant.MessageConstant}
     */
    private String system;
    private String msgContent;
    /**
     * 是否已消费，消费失败的情况：消费者接收到消息，业务执行失败
     */
    private Boolean consumed;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 初始化消息记录
     *
     * @param msgId       消息id
     * @param messageJson 消息对象json
     * @param system      消息源系统
     * @return
     */
    public static MessageLog build(String msgId, String messageJson, String system) {
        LocalDateTime now = LocalDateTime.now();
        return new MessageLog()
                .setMsgId(msgId)
                .setSystem(system)
                .setMsgContent(messageJson)
                .setConsumed(false)
                .setCreateTime(now)
                .setUpdateTime(now);
    }
}
