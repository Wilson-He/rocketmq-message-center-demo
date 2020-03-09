package io.wilson.message.service;

import io.wilson.common.message.BaseMessage;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 消息业务消费抽象接口，抽象每个消费者(Listener)调用的业务消费方法
 *
 * @author Wilson
 * @since 2020/3/4
 **/
public interface BaseMessageService<T extends BaseMessage> {

    /**
     * 消费消息
     *
     * @param message         消息
     * @param consumeFunction 消费方法
     * @return 消费结果
     */
        default boolean consume(T message, Function<T, Boolean> consumeFunction) {
        return consumeFunction.apply(message);
    }
}
