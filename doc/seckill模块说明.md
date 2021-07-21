# secKill模块说明


##功能说明

秒杀系统，redis rabbitmq 高并发实战

##数据库创建

Create table t_user(
id BIGINT(20) NOT NULL,
nickname VARCHAR(255) NOT NULL,
password VARCHAR(32) DEFAULT NULL COMMENT 'md5(md5(pass+slat))',
slat varchar(10) default null,
register_date datetime default null,
last_login_date datetime default null,
login_count int(11) default '0',
PRIMARY key(id)
)


