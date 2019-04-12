package com.qian.manager.controller;

import com.qian.entity.Product;
import com.qian.manager.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author : ChenQian
 * @date : 2019/4/12 17:50
 */
@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProduceService service;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        log.info("创建产品,参数:{}", product);
        Product result = service.addProduct(product);
        log.info("创建产品,结果:{}", result);
        return result;
    }

    @GetMapping(value = "/{id}")
    public Product findOne(@PathVariable String id) {
        log.info("查询单个产品,id:{}", id);
        Product product = service.findOne(id);
        log.info("查询单个产品,结果:{}", product);
        return product;
    }

    @GetMapping("/query")
    public Page<Product> query(String ids,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               String status,
                               @RequestParam(defaultValue = "0") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize) {
        log.info("查询列表,ids:{}, minRewardRate:{}, maxRewardRate:{}, " +
                        "status:{}, pageNum:{}, pageSize:{}",
                ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Product> page = service.query(idList, minRewardRate, maxRewardRate, statusList, pageable);
        log.info("查询产品,结果={}", page);
        return page;
    }
}

