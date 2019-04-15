package com.qian.manager.service;

import com.qian.api.event.ProductStatusEvent;
import com.qian.entity.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 管理产品状态
 *
 * @author : ChenQian
 * @date : 2019/4/15 16:50
 */
@Slf4j
@Component
public class ProductStatusManager {
    public static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void changeStatus(String id, ProductStatus status) {
        ProductStatusEvent event = new ProductStatusEvent(id, status);
        log.info("send message:{}", event);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
    }

    /**
     * 使用MQ更新缓存
     */
    //    @PostConstruct
    public void init() {
        changeStatus("001", ProductStatus.IN_SELL);
    }
}
