package com.whq.warehousemanagementsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 门店台账表
 * @TableName ledger
 */
@TableName(value ="ledger")
@Data
public class Ledger implements Serializable {
    /**
     * 商品流水ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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

    /**
     * 
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}