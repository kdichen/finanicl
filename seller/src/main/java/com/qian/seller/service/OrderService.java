package com.qian.seller.service;

import com.qian.entity.Order;
import com.qian.entity.Product;
import com.qian.entity.enums.OrderStatus;
import com.qian.entity.enums.OrderType;
import com.qian.seller.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 订单服务
 *
 * @author : ChenQian
 * @date : 2019/4/15 20:49
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRpcService productRpcService;

    /**
     * 申购订单
     *
     * @param order 订单
     * @return
     */
    public Order apply(Order order) {
        checkOrder(order);
        completeOrder(order);
        return orderRepository.saveAndFlush(order);
    }

    /**
     * 完善订单数据
     *
     * @param order 订单
     */
    private void completeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replaceAll("--", ""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
    }

    /**
     * 校验数据
     *
     * @param order 订单
     */
    private void checkOrder(Order order) {
        Assert.notNull(order.getOuterOrderId(), "缺少外部订单编号");
        Assert.notNull(order.getChanId(), "缺少渠道编号");
        Assert.notNull(order.getChanUserId(), "缺少用户编号");
        Assert.notNull(order.getProductId(), "缺少产品编号");
        Assert.notNull(order.getAmount(), "缺少购买金额");
        Assert.notNull(order.getCreateAt(), "缺少订单时间");
        Product product = productRpcService.findOne(order.getProductId());
        Assert.notNull(product, "产品不存在");
        // TODO 金额要满足如果有起投金额时，要大于等于起投金额，如果有投资步长时，超过起投金额的部分要是投资步长的整数倍
    }
}
