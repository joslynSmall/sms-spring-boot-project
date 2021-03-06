package com.github.jackieonway.sms.service;

import com.github.jackieonway.sms.entity.BaseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jackie
 * @version \$id: SmsService.java v 0.1 2019-05-11 10:11 Jackie Exp $$
 */
@Component
public interface SmsService {

    Logger log = LoggerFactory.getLogger(SmsService.class);

    /**
     * 单个发送短信
     *
     * @param params 根据对应的短信服务商所需信息填写
     * @return Object
     */
    Object sendSms(BaseRequest params);

    /**
     * 单个发送模板短信
     *
     * @param templateId 短信模板id
     * @param params     根据对应的短信服务商所需信息填写
     * @return Object
     */
    Object sendTemplateSms(@NonNull String templateId, BaseRequest params);

    /**
     * 批量发送短信
     *
     * @param params 根据对应的短信服务商所需信息填写
     * @return Object
     */
    Object sendBatchSms(@NonNull BaseRequest params);

    /**
     * 批量发送模板短信
     *
     * @param templateId 短信模板id
     * @param params     根据对应的短信服务商所需信息填写
     * @return Object
     */
    Object sendBatchTemplateSms(@NonNull String templateId, BaseRequest params);

    /**
     * 异步单个发送模板短信
     *
     * @param templateId 短信模板id
     * @param params     根据对应的短信服务商所需信息填写
     */
    @Async(value = "executorService")
    default void asyncSendTemplateSms(@NonNull String templateId, BaseRequest params) {
        Object sendTemplateSms = this.sendTemplateSms(templateId, params);
        log.info("Async Thread [{}] send sms , request templateId:[{}], " +
                "request prams:[{}], result:[{}]", Thread.currentThread().getName(), templateId, params,
                sendTemplateSms);
    }

    /**
     * 异步批量发送模板短信
     *
     * @param templateId 短信模板id
     * @param params     根据对应的短信服务商所需信息填写
     */
    @Async(value = "executorService")
    default void asnycSendBatchTemplateSms(@NonNull String templateId, BaseRequest params) {
        Object sendTemplateSms = this.sendTemplateSms(templateId, params);
        log.info("Async Thread [{}] send batch sms , request templateId:[{}], " +
                "request prams:[{}], result:[{}]", Thread.currentThread().getName(), templateId, params,
                sendTemplateSms);
    }
}
