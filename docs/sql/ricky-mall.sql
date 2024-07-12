create database if not exists `ricky-mall`;

use `ricky-mall`;

create table if not exists user
(
    id           bigint auto_increment primary key,
    email        varchar(32) not null comment '邮箱',
    password     varchar(32) not null comment '密码',
    nickname     varchar(16) not null comment '昵称',
    first_name   varchar(5)  not null comment '姓',
    last_name    varchar(10) not null comment '名',
    phone_number varchar(12) not null comment '手机号',
    role         tinyint     not null comment '用户角色',
    integral     bigint      not null comment '积分',
    level        int         not null comment '等级',
    balance      decimal     not null comment '余额',
    create_time  datetime    not null comment '创建时间',
    update_time  datetime    not null comment '修改时间'
)
    comment '用户表';

create table if not exists enterprise_user
(
    id            bigint auto_increment primary key,
    user_id       bigint      not null comment '用户id',
    record_number varchar(64) not null comment '备案号',
    company_name  varchar(64) not null comment '公司名',
    ceo           varchar(12) not null comment '首席执行官',
    create_time   datetime    not null comment '创建时间',
    update_time   datetime    not null comment '修改时间'
)
    comment '企业用户表';

create table if not exists business_user
(
    id            bigint auto_increment primary key,
    user_id       bigint      not null comment '用户id',
    store_name    varchar(32) not null comment '店铺名称',
    boss          varchar(12) not null comment '老板名称',
    record_number varchar(64) not null comment '备案号',
    create_time   datetime    not null comment '创建时间',
    update_time   datetime    not null comment '修改时间'
)
    comment '商家表';


