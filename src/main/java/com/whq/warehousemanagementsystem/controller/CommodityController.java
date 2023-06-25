package com.whq.warehousemanagementsystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whq.warehousemanagementsystem.domain.Commodity;
import com.whq.warehousemanagementsystem.domain.dto.commodity.CommodityAddRequest;
import com.whq.warehousemanagementsystem.domain.dto.commodity.CommodityDeleteRequest;
import com.whq.warehousemanagementsystem.domain.dto.commodity.CommodityModifyRequest;
import com.whq.warehousemanagementsystem.domain.dto.commodity.CommodityQueryRequest;
import com.whq.warehousemanagementsystem.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: whq
 * @description:
 * @time: 2023/6/25 19:12
 */
@RestController
@RequestMapping("/commodity")
@Api(tags = "商品模块接口")
@Slf4j
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    /**
     *
     * @param commodityAddRequest 新增商品请求类
     * @return false -- 新增失败    true -- 新增成功
     */
    @ApiOperation("新增商品")
    @GetMapping("/addCommodity")
    public Boolean addCommodity(CommodityAddRequest commodityAddRequest) {
        if (commodityAddRequest == null) {
            log.error("传递参数不能为空");
            return false;
        }
        String commodityName = commodityAddRequest.getCommodityName();
        Integer commodityPrice = commodityAddRequest.getCommodityPrice();
        String commoditySpecification = commodityAddRequest.getCommoditySpecification();
        Integer commodityStocks = commodityAddRequest.getCommodityStocks();
        if (StringUtils.isAnyEmpty(commodityName, commoditySpecification) || commodityPrice < 0 || commodityStocks < 0) {
            log.error("传递参数缺失");
            return false;
        }
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityAddRequest,commodity);
        return commodityService.save(commodity);
    }

    /**
     *
     * @param commodityModifyRequest 修改商品请求类
     * @return false -- 修改失败    true -- 修改成功
     */
    @ApiOperation("修改商品")
    @GetMapping("/modifyCommodityById")
    public Boolean modifyCommodityById(CommodityModifyRequest commodityModifyRequest) {
        if (commodityModifyRequest == null) {
            log.error("传递参数错误");
            return false;
        }
        Long id = commodityModifyRequest.getId();
        if (id < 0) {
            log.error("id传递错误");
            return false;
        }
        Commodity commodity = new Commodity();
        BeanUtils.copyProperties(commodityModifyRequest, commodity);
        return commodityService.updateById(commodity);
    }


    /**
     *
     * @param commodityDeleteRequest 删除商品请求类
     * @return false -- 删除失败    true -- 删除成功
     */
    @ApiOperation("删除商品")
    @GetMapping("/deleteCommodityById")
    public Boolean deleteCommodityById(CommodityDeleteRequest commodityDeleteRequest) {
        if (commodityDeleteRequest == null) {
            log.error("传递参数错误");
            return false;
        }
        Long id = commodityDeleteRequest.getId();
        if (id < 0) {
            log.error("id传递错误");
            return false;
        }
        return commodityService.removeById(id);
    }

    /**
     *
     * @param commodityQueryRequest 查询商品请求类
     * @return 查询结构
     * {
     *   "records": [
     *     {
     *       "id": 18,
     *       "commodityName": "测试商品2",
     *       "commoditySpecification": "大件物品",
     *       "commodityPrice": 12331,
     *       "createTime": "2023-06-25T12:26:07.000+00:00",
     *       "updateTime": "2023-06-25T12:26:07.000+00:00",
     *       "isDelete": 0
     *     }
     *   ],
     *   "total": 2,
     *   "size": 20,
     *   "current": 1,
     *   "orders": [],
     *   "optimizeCountSql": true,
     *   "searchCount": true,
     *   "countId": null,
     *   "maxLimit": null,
     *   "pages": 1
     * }
     */
    @ApiOperation("查询商品")
    @GetMapping("/getCommodityListByPage")
    public Page<Commodity> getCommodityListByPage(CommodityQueryRequest commodityQueryRequest) {
        long current = 1;
        long pageSize = 10;
        if (commodityQueryRequest == null) {
            log.error("参数为空");
            return null;
        }
        Commodity commodity = new Commodity();
        current = commodityQueryRequest.getCurrent();
        pageSize = commodityQueryRequest.getPageSize();
        BeanUtils.copyProperties(commodityQueryRequest, commodity);
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
        String commodityName = commodity.getCommodityName();
        if (commodityName != null) {
            queryWrapper.like("commodityName", commodityName);
        }
        queryWrapper.orderByDesc("createTime");
        Page<Commodity> page = commodityService.page(new Page<>(current, pageSize), queryWrapper);
        return page;
    }


}
