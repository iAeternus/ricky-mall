create database if not exists `ricky-mall`;

use `ricky-mall`;

create table if not exists user
(
    id            bigint auto_increment primary key,
    email         varchar(32) not null comment '邮箱',
    password      varchar(32) not null comment '密码',
    nickname      varchar(16) not null comment '昵称',
    first_name    varchar(5)  not null comment '姓',
    last_name     varchar(10) not null comment '名',
    phone_number  varchar(12) not null comment '手机号',
    role          tinyint     not null comment '用户角色',
    integral      bigint      not null comment '积分',
    level         int         not null comment '等级',
    balance       decimal     not null comment '余额',
    currency_code varchar(6)  not null comment '货币编码',
    create_time   datetime    not null comment '创建时间',
    update_time   datetime    not null comment '修改时间'
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

create table if not exists commodity
(
    id                   bigint auto_increment primary key,
    name                 varchar(32)   not null comment '商品名称',
    description          varchar(128)  not null comment '商品描述',
    price                double        not null comment '商品价格',
    currency_code        varchar(6)    null comment '货币编码',
    stock                int           not null comment '商品库存量',
    weight               double        not null comment '商品重量值',
    weight_unit          varchar(4)    not null comment '商品重量单位',
    commodity_type       tinyint       not null comment '商品状态',
    category_id          bigint        not null comment '分类id',
    brand_name           varchar(32)   null comment '品牌',
    discount_price       decimal       null comment '折扣价',
    promotion_start_time datetime      null comment '促销开始时间',
    promotion_end_time   datetime      null comment '促销结束时间',
    sold_count           int default 0 not null comment '销售数量',
    meta_title           varchar(32)   null comment 'SEO标题',
    meta_keywords        varchar(12)   null comment 'SEO关键词',
    meta_description     varchar(128)  null comment 'SEO描述',
    create_time          datetime      not null comment '创建时间',
    update_time          datetime      not null comment '修改时间'
)
    comment '商品表';

create table if not exists related_commodity
(
    id                   bigint auto_increment primary key,
    commodity_id         bigint   not null comment '商品id',
    related_commodity_id bigint   not null comment '相关商品ID',
    create_time          datetime not null comment '创建时间',
    update_time          datetime not null comment '修改时间'
)
    comment '关联商品表';

create table if not exists attributes
(
    id              bigint auto_increment primary key,
    commodity_id    bigint      not null comment '商品ID',
    attribute_key   varchar(16) not null comment '商品属性键',
    attribute_value varchar(32) not null comment '商品属性值',
    create_time     datetime    not null comment '创建时间',
    update_time     datetime    not null comment '修改时间'
)
    comment '商品属性表';

create table if not exists commodity_image
(
    id            bigint auto_increment primary key,
    commodity_id  bigint       not null comment '商品ID',
    name          varchar(32)  null comment '图片名',
    image_url     varchar(300) not null comment '图片URL',
    size_in_bytes bigint       null comment '图片大小，以字节为单位',
    create_time   datetime     not null comment '创建时间',
    update_time   datetime     not null comment '修改时间'
)
    comment '商品图片表';

create table if not exists supplier
(
    id           bigint auto_increment primary key,
    commodity_id bigint      not null comment '商品id',
    name         varchar(32) not null comment '供应商名称',
    contact      varchar(32) not null comment '联系方式',
    address      varchar(64) not null comment '地址',
    create_time  datetime    not null comment '创建时间',
    update_time  datetime    not null comment '修改时间'
)
    comment '供应商表';



