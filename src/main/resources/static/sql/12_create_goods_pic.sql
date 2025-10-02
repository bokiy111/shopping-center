create table goods_pic
(
    id bigint AUTO_INCREMENT comment 'pic的id',
    goods_id bigint not null comment '商品id',
    url varchar(255) not null comment '图片地址',
    create_time datetime default CURRENT_TIMESTAMP not null comment '注册时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (id)
)
    comment '商品图片表';