CREATE TABLE `hstest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rowname` varchar(20) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

CREATE TABLE `hstest2` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` tinyint DEFAULT '0',
  `createtime` timestamp NULL DEFAULT NULL,
  `money` double DEFAULT '0',
  `binary` binary(100) DEFAULT NULL,
  `real` float DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


