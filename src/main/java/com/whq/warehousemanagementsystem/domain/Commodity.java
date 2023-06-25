package com.whq.warehousemanagementsystem.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品
 * @TableName commodity
 */
@TableName(value ="commodity")
@Data
public class Commodity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品规格
     */
    private String commoditySpecification;

    /**
     * 商品单价
     */
    private Integer commodityPrice;

    /**
     * 商品库存
     */
    private Integer commodityStocks;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}