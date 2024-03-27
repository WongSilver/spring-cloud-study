create table t_account
(
    id      bigint auto_increment
        primary key,
    user_id bigint      null comment '用户ID',
    total   decimal(11) null comment '总额度',
    used    decimal(11) null comment '已用账户余额',
    residue decimal(11) null comment '余额'
);

INSERT INTO seata_account.t_account (id, user_id, total, used, residue) VALUES (1, 1, 1000, 20, 980);
