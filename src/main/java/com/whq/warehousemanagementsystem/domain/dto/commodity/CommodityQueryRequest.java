package com.whq.warehousemanagementsystem.domain.dto.commodity;

import com.baomidou.mybatisplus.annotation.*;
import com.whq.warehousemanagementsystem.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 * @TableName commodity
 */
@TableName(value ="commodity")
@Data
public class CommodityQueryRequest extends PageRequest implements Serializable {

    /**
     * 商品名称
     */
    private String commodityName;
}