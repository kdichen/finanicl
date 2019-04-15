package com.qian.seller.controller;

import com.qian.entity.Order;
import com.qian.seller.params.OrderParam;
import com.qian.seller.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单相关
 *
 * @author : ChenQian
 * @date : 2019/4/15 21:00
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/apply")
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody OrderParam orderParam) {
        log.info("申购请求:{}", orderParam);
        Order order = new Order();
        BeanUtils.copyProperties(orderParam, order);
        Order apply = orderService.apply(order);
        log.info("申购结果:{}", order);
        return order;
    }
}
