package com.qian.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.qian.api.domain.ParamInf;
import com.qian.entity.Product;

import java.util.List;

/**
 * 产品相关的rpc服务
 *
 * @author : ChenQian
 * @date : 2019/4/14 11:16
 */
@JsonRpcService
public interface ProductRpc {

    /**
     * 查询多个产品
     *
     * @param req 产品相关rpc请求对象
     * @return
     */
    List<Product> query(ParamInf req);

    /**
     * 查询单个产品
     *
     * @param id 请求id
     * @return
     */
    Product findOne(String id);
}
