package com.qian.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.qian.api.ProductRpc;
import com.qian.api.domain.ParamInf;
import com.qian.entity.Product;
import com.qian.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * rpc服务实现类
 *
 * @author : ChenQian
 * @date : 2019/4/14 11:29
 */
@Slf4j
@Service
@AutoJsonRpcServiceImpl
public class ProductRpcImpl implements ProductRpc {
    @Autowired
    private ProductService productService;

    /**
     * @param req 产品相关rpc请求对象
     * @return 查询多个产品查询结果
     */
    @Override
    public List<Product> query(ParamInf req) {
        log.info("查询多个产品,请求:{}", req);
        // 使用收益率倒叙排序
        Pageable pageable = new PageRequest(0, 100, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate(), req.getMaxRewardRate(),
                req.getStatusList(), pageable);
        log.info("查询多个产品,结果:{}", result);
        return result.getContent();
    }

    /**
     * 单个查询产品详情
     *
     * @param id 请求id
     * @return 单个产品详情查询结果
     */
    @Override
    public Product findOne(String id) {
        log.info("查询产品详情,请求:{}", id);
        Product result = productService.findOne(id);
        log.info("查询产品详情,结果:{}", result);
        return result;
    }
}
