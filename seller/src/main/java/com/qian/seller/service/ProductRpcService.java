package com.qian.seller.service;

import com.qian.api.event.ProductStatusEvent;
import com.qian.entity.Product;
import com.qian.entity.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 此处调用了查询缓存方法, 需要在容器初始化完成把所有数据加载进缓存
 * 产品相关服务
 *
 * @author : ChenQian
 * @date : 2019/4/14 11:41
 */
@Slf4j
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    private static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";


    @Autowired
    private ProductCache productCache;

    /**
     * 查询全部产品
     *
     * @return
     */
    public List<Product> findAll() {
        return productCache.readAllCache();
    }

    /**
     * 查询单个产品
     *
     * @param id 商品ID
     * @return
     */
    public Product findOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return product;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Product> all = findAll();
        all.forEach(product -> {
            productCache.putCache(product);
        });
    }

    /**
     * 使用MQ更新缓存
     *
     * @param event 产品状态对象
     */
    //    @JmsListener(destination = MQ_DESTINATION)
    void updateCache(ProductStatusEvent event) {
        log.info("receive event:{}", event);
        // 如果接受到消息,清空缓存再读取
        productCache.removeCache(event.getId());
        if (ProductStatus.IN_SELL.equals(event.getStatus())) {
            productCache.readCache(event.getId());
        }
    }
}


