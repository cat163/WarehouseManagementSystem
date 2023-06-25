package com.whq.warehousemanagementsystem.domain.dto.shopinfo;

import lombok.Data;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 22:52
 */
@Data
public class ShopInfoInbountRequest {

    /**
     * 入库数量
     */
    private int commodityInboundNum;

    /**
     * 商品ID
     */
    private int commodityId;

    /**
     * 门店ID
     */
    private int shopId;

}
