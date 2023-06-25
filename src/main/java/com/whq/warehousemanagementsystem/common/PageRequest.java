package com.whq.warehousemanagementsystem.common;

import lombok.Data;

/**
 * @author: whq
 * @description: 分页请求
 * @time: 2023/6/25 20:02
 */
@Data
public class PageRequest {
    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;
}
