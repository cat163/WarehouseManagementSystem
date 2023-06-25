-- auto-generated definition
create table commodity
(
    id                     bigint auto_increment comment 'id'
        primary key,
    commodityName          varchar(256)                       null comment '商品名称',
    commoditySpecification varchar(1024)                      null comment '商品规格',
    commodityPrice         int                                null comment '商品单价',
    commodityStocks        int      default 0                 null comment '商品库存',
    updateTime             datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete               tinyint  default 0                 not null comment '是否删除',
    createTime             datetime default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '总库商品';

create table AllLedger
(
    id                     bigint auto_increment comment '商品流水ID'
        primary key,
    commodityId          int                       null comment '商品Id',
    store          int                       null comment '所在门店Id',
    commodityOutboundNum         int  default 0                              null comment '商品出库数量',
    commodityInboundNum         int   default  0                            null comment '商品入库数量',
    updateTime             datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    createTime             datetime default CURRENT_TIMESTAMP null comment '创建时间',
    isDelete               tinyint  default 0                 not null comment '是否删除'
)
    comment '总库台账表';


create table ledger
(
    id                     bigint auto_increment comment '商品流水ID'
        primary key,
    commodityId          int                       null comment '商品Id',
    store          int                       null comment '所在门店Id',
    commodityOutboundNum         int     default  0                            null comment '商品出库数量',
    commodityInboundNum         int       default  0                          null comment '商品入库数量',
    updateTime             datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    createTime             datetime default CURRENT_TIMESTAMP null comment '创建时间',
    isDelete               tinyint  default 0                 not null comment '是否删除'
)
    comment '门店台账表';



create table shopInfo
(
    id                     bigint auto_increment comment '门店ID'
        primary key,
    shopName          varchar(256)                       null comment '门店名称',
    commodityId          varchar(1024)                       null comment '商品Id',
    updateTime             datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    createTime             datetime default CURRENT_TIMESTAMP null comment '创建时间',
    isDelete               tinyint  default 0                 not null comment '是否删除'
)
    comment '门店表';

create table nowShopStore
(
    id                     bigint auto_increment comment '店面的现存商品流水ID'
        primary key,
    shopId          int                     null comment '店面Id',
    shopName          varchar(256)                       null comment '门店名称',
    commodityId          int                     null comment '商品Id',
    commodityName          varchar(256)                       null comment '商品名称',
    commodityNum          int     default  0                   null comment '商品数量',
    updateTime             datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    createTime             datetime default CURRENT_TIMESTAMP null comment '创建时间',
    isDelete               tinyint  default 0                 not null comment '是否删除'
)
    comment '店面的现存商品关系表';

-- auto-generated definition
create table orderinfo
(
    id            bigint auto_increment comment '订单ID'
        primary key,
    shopId        int                      null comment '店面Id',
    commodityId   int                     null comment '商品Id',
    commodityName varchar(256)                       null comment '商品名称',
    commodityNum  int      default 0                 null comment '商品数量',
    userId        int                                null comment '用户ID',
    createTime    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    isDelete      tinyint  default 0                 not null comment '是否删除',
    updateTime    datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '商品订单表';

