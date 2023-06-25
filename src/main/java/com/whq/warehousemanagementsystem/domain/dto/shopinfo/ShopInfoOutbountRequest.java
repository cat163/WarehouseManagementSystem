package com.whq.warehousemanagementsystem.domain.dto.shopinfo;

import lombok.Data;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 22:49
 */
@Data
public class ShopInfoOutbountRequest {

    /**
     * 出库数量
     */
    private int commodityOutboundNum;

    /**
     * 商品ID
     */
    private int commodityId;

    /**
     * 门店ID
     */
    private int shopId;

}
