create table `t_pay`
(
    `id`          int(10) unsigned    not null auto_increment,
    `pay_no`      varchar(50)         not null comment '支付流水号',
    `order_no`    varchar(50)         not null comment '顶点流水号',
    `user_id`     int(10)                      default '1' comment '用户账号ID',
    `amount`      decimal(8, 2)       not null Default '9.9' comment '交易金额',
    `deleted`     tinyint(4) unsigned not null default '0' comment '删除标志, 默认0不删除,1删除',
    `create_time` timestamp           not null default current_timestamp comment '创建时间',
    `update_time` timestamp           not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`)
);

insert into t_pay(pay_no, order_no) values ('pay202403121420','202403121420');

select  * from t_pay
