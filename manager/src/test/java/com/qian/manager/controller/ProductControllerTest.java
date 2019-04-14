package com.qian.manager.controller;

import com.qian.entity.Product;
import com.qian.entity.enums.ProductStatus;
import com.qian.manager.repositories.ProductRepository;
import com.qian.util.RestUtil;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : ChenQian
 * @date : 2019/4/13 15:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

    private static RestTemplate rest = new RestTemplate();

    @Value("http://localhost:8081/manager")
    private String baseUrl;

    // 正常产品数据
    private static List<Product> normals = new ArrayList<>();

    //异常数据
    private static List<Product> exceptions = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void create() {
        normals.forEach(product -> {
            Product postJSON = RestUtil.postJSON(rest, baseUrl + "/products/addProduct", product, Product.class);
            Assert.notNull(postJSON.getCreateAt(), "插入失败");
        });
    }

    @Test
    public void createException() {
        exceptions.forEach(product -> {

            HashMap result = RestUtil.postJSON(rest, baseUrl + "/products/addProduct", product, HashMap.class);
             Assert.isTrue(result.get("message").equals(product.getName()), "插入成功");

        });
    }

    @Test
    public void findOne() {
        normals.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.isTrue(result.getId().equals(product.getId()), "查询失败");
        });
        exceptions.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.isNull(result, "查询失败");
        });
    }

    @Test
    @Transactional
    public void transaction() {
        normals.forEach(product -> {
            product.setLockTerm(0);
            productRepository.saveAndFlush(product);
        });
    }

    /*@Test
    public void zzzzClean() {
        productRepository.deleteAll(normals);
    }*/

    @BeforeClass
    public static void init() {
        Product p1 = new Product("T001", "灵活宝1号", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10), BigDecimal.valueOf(1), BigDecimal.valueOf(3.42));
        Product p2 = new Product("T002", "活期盈-金色人生", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(3.28));
        Product p3 = new Product("T003", "朝朝盈-聚财", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(100), BigDecimal.valueOf(10), BigDecimal.valueOf(3.86));
        normals.add(p1);
        normals.add(p2);
        normals.add(p3);

        Product e1 = new Product(null, "编号不可为空", ProductStatus.AUDITING.name(),
                BigDecimal.valueOf(10), BigDecimal.valueOf(1), BigDecimal.valueOf(2.34));
        Product e2 = new Product("E002", "未知异常", ProductStatus.AUDITING.name(),
                BigDecimal.ZERO, BigDecimal.valueOf(1), BigDecimal.valueOf(31));
        Product e3 = new Product("E003", "未知异常", ProductStatus.AUDITING.name(),
                BigDecimal.ZERO, BigDecimal.valueOf(1.01), BigDecimal.valueOf(3.44));

        exceptions.add(e1);
        exceptions.add(e2);
        exceptions.add(e3);

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        };
        rest.setErrorHandler(errorHandler);
    }

}