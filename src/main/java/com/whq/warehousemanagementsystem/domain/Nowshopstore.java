package com.whq.warehousemanagementsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 店面的现存商品关系表
 * @TableName nowshopstore
 */
@TableName(value ="nowshopstore")
@Data
public class Nowshopstore implements Serializable {
    /**
     * 店面的现存商品流水ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 店面Id
     */
    private Integer shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 商品Id
     */
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品数量
     */
    private Integer commodityNum;

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