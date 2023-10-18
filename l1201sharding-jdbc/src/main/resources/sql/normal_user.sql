--用于与分表不分库的course表join查询效果
CREATE TABLE `normal_user`  (
  `user_id` bigint(0) PRIMARY KEY NOT NULL,
  `username` varchar(100) NOT NULL,
  `ustatus` varchar(50) NOT NULL,
  `uage` int(3)
);
