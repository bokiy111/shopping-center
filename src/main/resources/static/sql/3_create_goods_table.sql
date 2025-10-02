create table goods
(
    id bigint AUTO_INCREMENT comment '商品id',
    name varchar(50) not null comment '商品名称',
    price varchar(20) not null comment '商品价格',
    description varchar(200) not null comment '商品详细信息',
    seller_id varchar(50) not null comment '卖家id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '商品上架时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '商品信息更新时间',
    primary key (id)

)
    comment '商品';