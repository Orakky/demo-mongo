# Regiser模块说明

## 数据库创建

```mysql
--创建sys_user--
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--创建sys_role--
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--创建sys_user_role--
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--插入数据--
INSERT INTO `sys_role` VALUES (1, 'ROLE_admin');
INSERT INTO `sys_role` VALUES (2, 'ROLE_user');

INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$LiJV/NxnZK2fiUemUthYCeazPXV/RzW5KQWhz5CZdYCbsUHxcRZWK');
INSERT INTO `sys_user` VALUES (2, 'user', '$2a$10$LiJV/NxnZK2fiUemUthYCeazPXV/RzW5KQWhz5CZdYCbsUHxcRZWK');

INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

```

