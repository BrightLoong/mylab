CREATE TABLE `BIND_RECORD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bind_uid` bigint(20) NOT NULL COMMENT '被绑uid',
  `bind_by_uid` bigint(20) NOT NULL COMMENT '渠道uid',
  `bind_type` int(2) NOT NULL COMMENT '绑定规则类型，1-长期，2-连续订阅，3-短期',
  `bind_rule_ref` bigint(20) NOT NULL COMMENT '绑定规则id',
  `start_time` datetime NOT NULL COMMENT '有效起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效终止时间',
  `extra` varchar(1024) DEFAULT NULL COMMENT '额外信息，json字段',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_bind_uid_type` (`bind_uid`,`bind_type`),
  KEY `idx_bind_uid` (`bind_uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='绑定记录表';

CREATE TABLE `BIND_RECORD_HISTORY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bind_uid` bigint(20) NOT NULL COMMENT '渠道uid',
  `bind_by_uid` bigint(20) NOT NULL COMMENT '被绑定方uid',
  `bind_type` int(2) NOT NULL COMMENT '绑定规则类型，1-长期，2-连续订阅，3-短期',
  `bind_rule_ref` bigint(20) NOT NULL COMMENT '绑定规则id',
  `start_time` datetime NOT NULL COMMENT '有效起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '有效终止时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extra` varchar(1024) DEFAULT NULL COMMENT '额外信息，json字段',
  PRIMARY KEY (`id`),
  KEY `idx_bind_uid` (`bind_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb4 COMMENT='绑定记录历史表，删除的绑定规则会移到历史表中';

INSERT INTO `xmds_bind`.`BIND_RECORD`(`id`, `bind_uid`, `bind_by_uid`, `bind_type`, `bind_rule_ref`, `start_time`, `end_time`, `extra`, `created_time`, `last_updated_time`) VALUES (244, 57431, 205449, 1, 75, '2018-12-07 12:43:59', '2018-12-01 12:43:59', NULL, '2018-11-29 10:55:30', '2019-02-13 16:35:40');