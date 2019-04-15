package com.qian.api.event;

import com.qian.entity.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品状态
 *
 * @author : ChenQian
 * @date : 2019/4/15 16:31
 */
@Data
@AllArgsConstructor
public class ProductStatusEvent implements Serializable {

    /**
     * 产品编号
     */
    private String id;

    /***
     * 产品状态
     */
    private ProductStatus status;

}
