package com.whq.warehousemanagementsystem.domain.dto.nowshopstore;

import lombok.Data;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 23:18
 */
@Data
public class NowshopstoreGetRequest {
    /**
     * 店面Id
     */
    private Integer shopId;

    /**
     * 商品Id
     */
    private Integer commodityId;
}
