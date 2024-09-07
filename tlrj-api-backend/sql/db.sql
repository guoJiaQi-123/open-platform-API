use tlrj_api;
-- 接口信息表-
create table if not exists tlrj_api.`interface_info`
(
    `id`             bigint                             not null auto_increment comment '主键' primary key,
    `name`           varchar(256)                       not null comment '接口名称',
    `description`    varchar(256)                       null comment '接口描述',
    `url`            varchar(512)                       not null comment '接口地址',
    `method`         varchar(256)                       not null comment '接口方法',
    `requestHeader`  text                               null comment '请求头',
    `responseHeader` text                               null comment '响应头',
    `status`         int                                not null comment '接口状态',
    `userId`         bigint                             not null comment '接口创建者',
    `createTime`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`     tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息表-' collate = utf8mb4_unicode_ci;

insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('nq7C', 'vwE7', 'www.nu-streich.org', '5Nxg', 'jdJXz', 'AT', 0, 2875);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('Fl2O', 'xmJmo', 'www.adella-howell.name', 'LxGCm', '9iHSq', 'eSzH', 0, 75733544);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('FvB', 'FNb', 'www.basil-schiller.io', 'PQ', 'TNJX', '8FLy', 0, 509033279);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('M8tLw', 'ofNn', 'www.adelaida-fisher.io', 'M6', 'h89', 'VZqR', 0, 48);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('0ljd', 'Rbn7V', 'www.kelly-hauck.co', 'zBF11', 'US2', 'sL', 0, 410602979);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('6z', 'CK', 'www.dewayne-hegmann.info', 'pWmtS', 'RTbxj', 'eQs1', 0, 165425);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('eja', 'qIPNv', 'www.napoleon-lockman.io', 'sO', 'BOWf6', 'vPzTF', 0, 118766135);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('eT', 'uTspA', 'www.monty-bode.com', 'TkZX6', 'L2', 'GWj', 0, 56133994);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('tS', 'ftgH', 'www.ricky-mitchell.name', 'mB', 'AySlN', 'Vac', 0, 9446183364);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('eIgs', 'Gz', 'www.delois-krajcik.name', 's2', 'PbNox', 'MisLh', 0, 214);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('yLT', 'zvPK', 'www.whitney-larkin.net', 'Ltwwl', 'UK', 'L6g', 0, 41871);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('bP3', '6R6O', 'www.burt-spencer.net', 'vvl', '7ahE', 'w5n6C', 0, 4);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('rF', 'eZ', 'www.columbus-marquardt.info', 'a7zW', 'vjOzI', 'K68n', 0, 30);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('cVUL', 'osh1', 'www.alvina-haag.biz', 'IP', 'wHKy', 'XsPZ', 0, 1824879);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('2B3U', 'f1VcL', 'www.blake-jerde.name', 'U7Ws', '2t', 'Y2', 0, 88820306);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('Sz', 'Xl5s', 'www.caren-boyer.net', 'yDumf', 'npZcn', '7Nm49', 0, 839);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('Cg7L0', 'KM8', 'www.jonathan-reinger.co', 'ooXc', '9cnYy', 'rnXE', 0, 196);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('pjeR2', 'czr', 'www.clinton-carroll.io', 'NQI', 'Yldp3', 'ETx', 0, 13202);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('uj', 'lmdpf', 'www.riley-schneider.net', 'LH', 'CuHBb', '7ITGM', 0, 4850145);
insert into tlrj_api.`interface_info` (`name`, `description`, `url`, `method`, `requestHeader`, `responseHeader`,
                                       `status`, `userId`)
values ('gtKbw', '1e', 'www.francisca-frami.io', '7ol', 'NQBNL', 'Zb', 0, 396606);