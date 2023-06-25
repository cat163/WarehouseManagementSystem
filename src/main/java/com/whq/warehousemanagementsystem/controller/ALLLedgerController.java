package com.whq.warehousemanagementsystem.controller;

import com.whq.warehousemanagementsystem.domain.Allledger;
import com.whq.warehousemanagementsystem.domain.dto.allledger.AllLedgerCreateRequest;
import com.whq.warehousemanagementsystem.service.AllledgerService;
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
 * @time: 2023/6/25 21:54
 */
@RestController
@RequestMapping("/allLedger")
@Api(tags = "总库台账表")
@Slf4j
public class ALLLedgerController {

    @Resource
    private AllledgerService allledgerService;

    /**
     *
     * @param allLedgerCreateRequest
     * @return false -- 创建失败  true -- 创建成功
     */
    @ApiOperation("创建流水")
    @GetMapping("/createLedger")
    public Boolean createLedger(AllLedgerCreateRequest allLedgerCreateRequest) {
        if (allLedgerCreateRequest == null) {
            log.error("参数传递错误");
            return false;
        }
        Integer store = allLedgerCreateRequest.getStore();
        Integer commodityId = allLedgerCreateRequest.getCommodityId();
        if (store < 0 || commodityId < 0) {
            log.error("传递编号不对");
            return false;
        }
        Allledger allledger = new Allledger();
        BeanUtils.copyProperties(allLedgerCreateRequest, allledger);
        return allledgerService.save(allledger);
    }

}
