create table user
(
    id bigint AUTO_INCREMENT comment '用户id',
    account varchar(20) not null comment '账号',
    encrypted_password varchar(100) not null comment '加密密码',
    nickname varchar(20) not null comment '用户名',
    avatar_url varchar(200) not null comment '头像链接',
    create_time datetime default CURRENT_TIMESTAMP not null comment '注册时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (id),
    unique (account)
)
    comment '用户';