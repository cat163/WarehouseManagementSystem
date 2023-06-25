package com.whq.warehousemanagementsystem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 门店表
 * @TableName shopinfo
 */
@TableName(value ="shopinfo")
@Data
public class Shopinfo implements Serializable {
    /**
     * 门店ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 商品Id
     */
    private String commodityId;

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