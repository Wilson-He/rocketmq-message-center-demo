package io.wilson.common.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Wilson
 * @since 2020/3/2
 **/
@Data
@Accessors(chain = true)
public abstract class BaseMessage implements Serializable {
    /**
     * 消息源系统:{@link io.wilson.common.message.constant.MessageConstant.System}
     */
    private String system;
}
