package com.qian.seller.controller;

import com.qian.entity.Product;
import com.qian.seller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品相关
 *
 * @author : ChenQian
 * @date : 2019/4/15 14:52
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRpcService productRpcService;

    @GetMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        return productRpcService.findOne(id);

    }
}
