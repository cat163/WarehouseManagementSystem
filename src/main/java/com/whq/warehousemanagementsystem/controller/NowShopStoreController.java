package com.whq.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whq.warehousemanagementsystem.domain.Nowshopstore;
import com.whq.warehousemanagementsystem.domain.dto.nowshopstore.NowshopstoreGetRequest;
import com.whq.warehousemanagementsystem.domain.dto.nowshopstore.NowshopstoreUpdateRequest;
import com.whq.warehousemanagementsystem.service.NowshopstoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 23:15
 */
@RestController
@RequestMapping("/store")
@Api(tags = "店面的现存商品关系表")
@Slf4j
public class NowShopStoreController {

    @Resource
    private NowshopstoreService nowshopstoreService;

    @ApiOperation("创建流水")
    @GetMapping("/updateStore")
    public Boolean updateStore(NowshopstoreUpdateRequest nowshopstoreUpdateRequest) {
        if (nowshopstoreUpdateRequest == null) {
            log.error("参数传递错误");
            return false;
        }
        Integer shopId = nowshopstoreUpdateRequest.getShopId();
        Integer commodityId = nowshopstoreUpdateRequest.getCommodityId();
        if (shopId < 0 || commodityId < 0) {
            log.error("参数错误");
            return false;
        }
        QueryWrapper<Nowshopstore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commodityId",commodityId);
        queryWrapper.eq("shopId",shopId);

        if (nowshopstoreService.count(queryWrapper) > 0) {
            // 商品记录存在
            Nowshopstore old = nowshopstoreService.getOne(queryWrapper);
            old.setCommodityNum(nowshopstoreUpdateRequest.getCommodityNum());
            return nowshopstoreService.updateById(old);
        } else {
            Nowshopstore nowshopstore = new Nowshopstore();
            BeanUtils.copyProperties(nowshopstoreUpdateRequest, nowshopstore);
            return nowshopstoreService.save(nowshopstore);
        }
    }

    @ApiOperation("获取当前商品数目")
    @GetMapping("/getStore")
    public Integer getStore(NowshopstoreGetRequest nowshopstoreGetRequest) {
        if (nowshopstoreGetRequest == null) {
            log.error("参数传递错误");
            return null;
        }
        Integer shopId = nowshopstoreGetRequest.getShopId();
        Integer commodityId = nowshopstoreGetRequest.getCommodityId();
        if (shopId < 0 || commodityId < 0) {
            log.error("参数错误");
            return null;
        }
        QueryWrapper<Nowshopstore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commodityId",commodityId);
        queryWrapper.eq("shopId",shopId);

        if (nowshopstoreService.count(queryWrapper) > 0) {
            // 商品记录存在
            Nowshopstore old = nowshopstoreService.getOne(queryWrapper);
            return old.getCommodityNum();
        }
        return null;
    }

}
