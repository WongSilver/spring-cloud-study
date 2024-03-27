create table t_order
(
    id         bigint auto_increment
        primary key,
    user_id    bigint      null comment '用户ID',
    product_id bigint      null comment '产品ID',
    count      int         null comment '数量',
    money      decimal(11) null comment '金额',
    status     int         null comment '订单状态: 0:创建中, 1:已完结'
);

INSERT INTO seata_order.t_order (id, user_id, product_id, count, money, status) VALUES (17, 1, 1, 1, 1, 1);
INSERT INTO seata_order.t_order (id, user_id, product_id, count, money, status) VALUES (23, 1, 1, 1, 10, 1);
