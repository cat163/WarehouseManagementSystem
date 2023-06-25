package com.whq.warehousemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whq.warehousemanagementsystem.domain.Commodity;
import com.whq.warehousemanagementsystem.service.CommodityService;
import com.whq.warehousemanagementsystem.mapper.CommodityMapper;
import org.springframework.stereotype.Service;

/**
* @author 35029
* @description 针对表【commodity(商品)】的数据库操作Service实现
* @createDate 2023-06-25 19:08:56
*/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity>
    implements CommodityService{

}




