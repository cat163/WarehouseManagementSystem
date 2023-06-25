package com.whq.warehousemanagementsystem.domain.dto.allledger;

import lombok.Data;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 23:10
 */
@Data
public class AllLedgerCreateRequest {

    /**
     * 商品Id
     */
    private Integer commodityId;

    /**
     * 所在门店Id
     */
    private Integer store;

    /**
     * 商品出库数量
     */
    private Integer commodityOutboundNum;

    /**
     * 商品入库数量
     */
    private Integer commodityInboundNum;


}
