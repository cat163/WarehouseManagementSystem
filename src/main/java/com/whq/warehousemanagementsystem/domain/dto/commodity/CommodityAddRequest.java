package com.whq.warehousemanagementsystem.domain.dto.commodity;

import lombok.Data;

/**
 * @author: whq
 * @description: 商品添加请求类
 * @time: 2023/6/25 19:30
 */
@Data
public class CommodityAddRequest {

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品规格
     */
    private String commoditySpecification;

    /**
     * 商品单价
     */
    private Integer commodityPrice;

    /**
     * 商品库存
     */
    private Integer commodityStocks;
}
