package io.wilson.message.listener;

import com.alibaba.fastjson.JSONObject;
import io.wilson.common.message.BaseMessage;
import io.wilson.message.domain.MessageLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

/**
 * AbstractMQStoreListener
 *
 * @author Wilson
 * @since 2020/3/5
 **/
@Slf4j
public abstract class AbstractMQStoreListener {

    @Resource
    protected MongoTemplate mongoTemplate;

    /**
     * 判断消息是否已被消费
     *
     * @param msgId
     * @return
     */
    boolean isConsumed(String msgId) {
        long count = mongoTemplate.count(new Query(Criteria.where("msg_id").is(msgId)), collection());
        if (count > 0) {
            log.info("消息{}已成功消费过，请勿重复投递!", msgId);
            return true;
        }
        return false;
    }

    /**
     * 当前消息的mongo collection名:{@link io.wilson.message.domain.constant.MessageLogConstant.CollectionName}
     *
     * @return 当前消息存储的collection名
     */
    abstract String collection();

    /**
     * 保存消息消费记录
     *
     * @param success 业务执行结果
     * @param msgId   消息id
     * @param message
     */
    void store(boolean success, String msgId, BaseMessage message) {
        MessageLog messageLog = MessageLog.convertFromMessage(message)
                .setMsgId(msgId)
                .setMsgContent(JSONObject.toJSONString(message))
                .setSuccess(success);
        mongoTemplate.insert(messageLog, collection());
    }
}
