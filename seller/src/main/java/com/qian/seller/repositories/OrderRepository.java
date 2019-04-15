package com.qian.seller.repositories;

import com.qian.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 订单管理
 *
 * @author : ChenQian
 * @date : 2019/4/15 20:47
 */

public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}
