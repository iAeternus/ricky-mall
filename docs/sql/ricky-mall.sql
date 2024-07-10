create database if not exists `ricky-mall`;

use `ricky-mall`;

create table if not exists user
(
    id           bigint auto_increment primary key,
    username     varchar(16) not null comment '用户名',
    password     varchar(32) not null comment '密码',
    first_name   varchar(5)  not null comment '姓',
    last_name    varchar(5)  not null comment '名',
    email        varchar(32) not null comment '邮箱',
    phone_number varchar(12) not null comment '手机号',
    role         tinyint     not null comment '用户角色',
    create_time  datetime    not null comment '创建时间',
    update_time  datetime    not null comment '修改时间'
)
    comment '用户表';

