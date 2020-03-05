package io.wilson.message.exception;

import io.springframework.common.exception.AppException;
import lombok.Getter;

/**
 * @author Wilson
 * @since 2020/3/5
 **/
@Getter
public class MQConsumeException extends AppException {
    public MQConsumeException(String message) {
        super(message);
    }

    public MQConsumeException(Integer code, String message) {
        super(code, message);
    }
}
