package com.whq.warehousemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whq.warehousemanagementsystem.domain.Orderinfo;
import com.whq.warehousemanagementsystem.service.OrderinfoService;
import com.whq.warehousemanagementsystem.mapper.OrderinfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 35029
* @description 针对表【orderinfo(商品订单表)】的数据库操作Service实现
* @createDate 2023-06-25 22:50:45
*/
@Service
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoMapper, Orderinfo>
    implements OrderinfoService{

}




