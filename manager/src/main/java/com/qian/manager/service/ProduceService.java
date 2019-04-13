package com.qian.manager.service;

import com.qian.entity.Product;
import com.qian.entity.enums.ProductStatus;
import com.qian.manager.error.ErrorEnum;
import com.qian.manager.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : ChenQian
 * @date : 2019/4/12 17:33
 */

@Slf4j
@Service
public class ProduceService {

    @Autowired
    private ProductRepository repository;

    public Product addProduct(Product product) {
        log.debug("创建产品,参数:{}", product);
        checkProduct(product);
        setDefault(product);
        Product save = repository.save(product);
        log.debug("创建产品,结果:{}", product);
        return save;

    }

    public Product findOne(String id) {
        Assert.notNull(id, "ID不能为空");
        log.debug("查询单个产品, id:{}", id);
        Product product = repository.findOne(id);
        log.debug("查询单个产品, 结果:{}", product);
        return product;

    }

    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               List<String> statusList,
                               Pageable pageable) {
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if (idList != null && idList.size() > 0) {
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicates.add(cb.ge(rewardRateCol, minRewardRate));
                }
                if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicates.add(cb.le(rewardRateCol, maxRewardRate));
                }
                if (statusList != null && statusList.size() > 0) {
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> page = repository.findAll(specification, pageable);
        log.info("查询产品带条件,结果:{}", page);
        return page;
    }

    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo
                (product.getRewardRate()) >= 0, "收益率范围出错");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) ==
                0, "投资步长需要是整数");
    }

    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }
}
