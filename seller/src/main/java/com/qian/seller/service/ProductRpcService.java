package com.qian.seller.service;

import com.qian.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品相关服务
 *
 * @author : ChenQian
 * @date : 2019/4/14 11:41
 */
@Service
public class ProductRpcService {


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
     * @param id
     * @return
     */
    public Product findOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return product;
    }
}


