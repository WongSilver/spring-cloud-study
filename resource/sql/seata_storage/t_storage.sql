create table t_storage
(
    id         bigint auto_increment
        primary key,
    product_id bigint      null comment '用户ID',
    total      decimal(11) null comment '总库存',
    used       decimal(11) null comment '已用库存',
    residue    decimal(11) null comment '剩余库存'
);

INSERT INTO seata_storage.t_storage (id, product_id, total, used, residue) VALUES (1, 1, 100, 2, 98);
