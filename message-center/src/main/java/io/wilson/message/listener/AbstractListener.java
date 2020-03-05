package io.wilson.message.listener;

import io.wilson.common.message.BaseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

/**
 * @author Wilson
 * @since 2020/3/5
 **/
@Slf4j
public abstract class AbstractListener<T extends BaseMessage> {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 判断消息是否已被消费
     *
     * @param msgId
     * @return
     */
    protected boolean isConsumed(String msgId) {
        long count = mongoTemplate.count(new Query(Criteria.where("msg_id").is(msgId))
                        .addCriteria(Criteria.where("consumed").is(true))
                , collection());
        if (count > 0) {
            log.info("消息{}已成功消费过，请勿重复投递!", msgId);
            return true;
        }
        return false;
    }

    /**
     * 当前service负责的mongo collection名:{@link io.wilson.message.domain.constant.MessageLogConstant.CollectionName}
     *
     * @return 当前service负责的collection名
     */
    protected abstract String collection();
}
