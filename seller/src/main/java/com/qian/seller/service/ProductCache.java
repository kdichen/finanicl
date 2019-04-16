package com.qian.seller.service;

import com.hazelcast.core.HazelcastInstance;
import com.qian.api.ProductRpc;
import com.qian.api.domain.ProductRpcReq;
import com.qian.entity.Product;
import com.qian.entity.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品缓存
 *
 * @author : ChenQian
 * @date : 2019/4/14 11:52
 */
@Slf4j
@Component
public class ProductCache {

    private static final String CACHE_NAME = "qian_product";

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * 读取缓存,查询单个产品
     *
     * @param id 产品ID
     * @return 返回缓存中单个产品信息
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        log.info("rpc查询单个产品,请求:{}", id);
        Product product = productRpc.findOne(id);
        log.info("rpc查询单个产品,结果:{}", product);
        return product;
    }

    /**
     * product.id为key, 把product信息放入缓存中
     *
     * @param product 商品信息
     * @return
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {
        return product;

    }

    /**
     * 如果缓存中有数据则清除
     *
     * @param id 商品ID
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {

    }

    /**
     * 从缓存中获取全部数据
     *
     * @return 返回缓存中全部产品信息
     */

    public List<Product> readAllCache() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size() > 0) {
            products = new ArrayList<>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }

    /**
     * 查询全部产品
     *
     * @return
     */
    private List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusList(status);
        log.info("rpc查询全部产品,请求:{}", req);
        List<Product> result = productRpc.query(req);
        log.info("rpc查询全部产品,结果:{}", result);
        return result;
    }
}
