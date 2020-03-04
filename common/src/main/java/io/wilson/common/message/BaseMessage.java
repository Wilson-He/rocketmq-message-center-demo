package io.wilson.common.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Data
public abstract class BaseMessage implements Serializable {
    /**
     * 消息id
     */
    protected String msgId;
}
