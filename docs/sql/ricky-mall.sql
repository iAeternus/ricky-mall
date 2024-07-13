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

create table if not exists commodity
(
    id                   bigint auto_increment primary key,
    name                 varchar(32)   not null comment '商品名称',
    description          varchar(128)  not null comment '商品描述',
    price                json          not null comment '商品价格',
    stock                int           not null comment '商品库存量',
    commodity_type       tinyint       not null comment '商品状态',
    main_image_url       varchar(300)  not null comment '主图URL',
    category_id          bigint        not null comment '分类id',
    brand                varchar(32)   null comment '品牌',
    original_price       json          null comment '原价',
    discount_price       json          null comment '折扣价',
    promotion_start_time datetime      null comment '促销开始时间',
    promotion_end_time   datetime      null comment '促销结束时间',
    sold_count           int default 0 not null comment '销售数量',
    weight               double        not null comment '商品重量值',
    weight_unit          varchar(4)    not null comment '商品重量单位',
    supplier_id          bigint        not null comment '供应商ID',
    meta_title           varchar(32)   null comment 'SEO标题',
    meta_keywords        varchar(12)   null comment 'SEO关键词',
    meta_description     varchar(128)  null comment 'SEO描述',
    create_time          datetime      not null comment '创建时间',
    update_time          datetime      not null comment '修改时间'
)
    comment '商品表';

create table if not exists associated_commodity
(
    id                   bigint auto_increment primary key,
    commodity_id         bigint   not null comment '商品id',
    related_commodity_id bigint   not null comment '相关商品ID',
    sku_id               int      null comment '对应的SKU ID',
    create_time          datetime not null comment '创建时间',
    update_time          datetime not null comment '修改时间'
)
    comment '关联商品表';

create table if not exists attributes
(
    id           bigint auto_increment primary key,
    commodity_id bigint      not null comment '商品ID',
    `key`        varchar(16) not null comment '商品属性键',
    value        varchar(32) not null comment '商品属性值',
    create_time  datetime    not null comment '创建时间',
    update_time  datetime    not null comment '修改时间'
)
    comment '商品属性表';

create table if not exists gallery_image
(
    id           bigint auto_increment primary key,
    commodity_id bigint       not null comment '商品ID',
    image_url    varchar(300) not null comment '图片URL',
    create_time  datetime     not null comment '创建时间',
    update_time  datetime     not null comment '修改时间'
)
    comment '商品图片表';



