package com.qian.manager.repositories;

import com.qian.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : ChenQian
 * @date : 2019/4/12 17:30
 */

public interface ProductRepository extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product> {
}
