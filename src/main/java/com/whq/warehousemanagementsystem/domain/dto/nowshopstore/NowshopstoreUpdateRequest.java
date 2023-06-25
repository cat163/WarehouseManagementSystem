package com.whq.warehousemanagementsystem.domain.dto.nowshopstore;

import lombok.Data;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 23:18
 */
@Data
public class NowshopstoreUpdateRequest {
    /**
     * 店面Id
     */
    private Integer shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 商品Id
     */
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品数量
     */
    private Integer commodityNum;
}
