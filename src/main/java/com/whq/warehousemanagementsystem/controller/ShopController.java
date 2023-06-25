package com.whq.warehousemanagementsystem.controller;


import com.whq.warehousemanagementsystem.domain.Commodity;
import com.whq.warehousemanagementsystem.domain.dto.allledger.AllLedgerCreateRequest;
import com.whq.warehousemanagementsystem.domain.dto.nowshopstore.NowshopstoreGetRequest;
import com.whq.warehousemanagementsystem.domain.dto.nowshopstore.NowshopstoreUpdateRequest;
import com.whq.warehousemanagementsystem.domain.dto.shopinfo.ShopInfoInbountRequest;
import com.whq.warehousemanagementsystem.domain.dto.shopinfo.ShopInfoOutbountRequest;
import com.whq.warehousemanagementsystem.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 22:02
 */
@RestController
@RequestMapping("/shop")
@Api(tags = "门店模块接口")
@Slf4j
public class ShopController {

    @Resource
    private CommodityService commodityService;

    @ApiOperation("出库")
    @GetMapping("/outbound")
    public Integer outbound(ShopInfoOutbountRequest shopInfoOutbountRequest) {
        /**
         * 出库 -> 门店商品减 -> 门店台账流水 -> 订单流水
        */
        if (shopInfoOutbountRequest == null) {
            log.error("参数为空");
            return null;
        }
        int commodityOutboundNum = shopInfoOutbountRequest.getCommodityOutboundNum();
        int commodityId = shopInfoOutbountRequest.getCommodityId();
        int shopId = shopInfoOutbountRequest.getShopId();
        if (commodityOutboundNum < 0 || commodityId < 0 || shopId < 0) {
            log.error("参数传递错误");
            return null;
        }
        // 获取当前库存
        NowshopstoreGetRequest nowshopstoreGetRequest = new NowshopstoreGetRequest();
        nowshopstoreGetRequest.setShopId(shopId);
        nowshopstoreGetRequest.setCommodityId(commodityId);
        NowShopStoreController nowShopStoreController = new NowShopStoreController();
        Integer store = nowShopStoreController.getStore(nowshopstoreGetRequest);
        int stock = store - commodityOutboundNum;
        if (stock < 0) {
            log.error("库存不够");
            return null;
        }
        // 更新门店商品
        NowshopstoreUpdateRequest nowshopstoreUpdateRequest = new NowshopstoreUpdateRequest();
        nowshopstoreUpdateRequest.setShopId(shopId);
        nowshopstoreUpdateRequest.setCommodityId(commodityId);
        nowshopstoreUpdateRequest.setCommodityNum(stock);
        nowShopStoreController.updateStore(nowshopstoreUpdateRequest);

        return nowShopStoreController.getStore(nowshopstoreGetRequest);
    }

    /**
     *
     * @param shopInfoInbountRequest
     * @return 返回指定门店的商品现存数量
     */
    @ApiOperation("入库")
    @GetMapping("/inbound")
    public Integer inbound(ShopInfoInbountRequest shopInfoInbountRequest) {
        /**
         * 入库 -> 总库商品减 门店商品加 -> 总库流水帐 门店台账流水
         */
        if (shopInfoInbountRequest == null) {
            log.error("参数为空");
            return null;
        }
        int commodityInboundNum = shopInfoInbountRequest.getCommodityInboundNum();
        int commodityId = shopInfoInbountRequest.getCommodityId();
        int shopId = shopInfoInbountRequest.getShopId();
        if (commodityInboundNum < 0 || commodityId < 0 || shopId < 0) {
            log.error("参数传递错误");
            return null;
        }
        Commodity commodity = commodityService.getById(commodityId);
        Integer commodityStocks = commodity.getCommodityStocks();
        int stock = commodityStocks - commodityInboundNum;
        if (stock < 0) {
            log.error("货物不够");
            return null;
        }
        commodity.setCommodityStocks(stock);
        // 在更新之前，可以根据系统业务来判断是否有这权限，比如店铺ID传过来，但是根据判断该店铺无资格购买等等
        // 更新总库存
        commodityService.updateById(commodity);

        // 更新总流水账
        ALLLedgerController allLedgerController = new ALLLedgerController();
        AllLedgerCreateRequest allLedgerCreateRequest = new AllLedgerCreateRequest();
        allLedgerCreateRequest.setCommodityId(commodityId);
        allLedgerCreateRequest.setStore(shopId);
        allLedgerCreateRequest.setCommodityOutboundNum(commodityInboundNum);
        allLedgerCreateRequest.setCommodityInboundNum(0);
        allLedgerController.createLedger(allLedgerCreateRequest);

        // 更新门店商品
        NowShopStoreController nowShopStoreController = new NowShopStoreController();
        NowshopstoreUpdateRequest nowshopstoreUpdateRequest = new NowshopstoreUpdateRequest();
        nowshopstoreUpdateRequest.setShopId(shopId);
        nowshopstoreUpdateRequest.setCommodityId(commodityId);

        NowshopstoreGetRequest nowshopstoreGetRequest = new NowshopstoreGetRequest();
        nowshopstoreGetRequest.setShopId(shopId);
        nowshopstoreGetRequest.setCommodityId(commodityId);
        Integer store = nowShopStoreController.getStore(nowshopstoreGetRequest);
        // 判断是否还有新库存
        if (store == null) {
            nowshopstoreUpdateRequest.setCommodityNum(commodityInboundNum);
        } else {
            nowshopstoreUpdateRequest.setCommodityNum(commodityInboundNum + store);
        }
        nowShopStoreController.updateStore(nowshopstoreUpdateRequest);

        // 返回指定门店的商品现存数量
        return nowShopStoreController.getStore(nowshopstoreGetRequest);
    }

}
