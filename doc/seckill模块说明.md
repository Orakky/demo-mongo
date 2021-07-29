# secKill模块说明


##功能说明

秒杀系统，redis rabbitmq 高并发实战

##数据库创建

Create table t_user(
id VARCHAR(20) NOT NULL,
nickname VARCHAR(255) NOT NULL,
password VARCHAR(32) DEFAULT NULL COMMENT 'md5(md5(pass+slat))',
slat varchar(10) default null,
register_date datetime default null,
last_login_date datetime default null,
login_count int(11) default '0',
PRIMARY key(id)
)


CREATE TABLE t_goods(
id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
goods_name VARCHAR(20) DEFAULT NULL COMMENT '商品名称',
goods_title VARCHAR(64) DEFAULT NULL COMMENT '商品标题',
goods_img VARCHAR(64) DEFAULT NULL COMMENT '商品图片',
goods_detail LONGTEXT COMMENT '商品详情',
goods_price DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品价格',
goods_stock INT(11) DEFAULT '0' COMMENT '商品库存 -1 表示没有限制',
PRIMARY key(id)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET = utf8mb4;



CREATE TABLE t_order(
id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
user_id bigint(20) DEFAULT NULL COMMENT '用户id',
goods_id bigint(20) DEFAULT NULL COMMENT '商品id',
delivery_addr_id bigint(20) default null comment '收货地址id',
goods_name VARCHAR(20) DEFAULT NULL COMMENT '冗余的商品名称',
goods_count INT(11) DEFAULT '0' COMMENT '商品数量',
goods_price DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品单价',
order_channel TINYINT(4) DEFAULT '0' COMMENT '1PC 2ANDROID 3IOS',
status TINYINT(4) DEFAULT '0' COMMENT '订单状态 0新建未支付 1已支付 2已发货 3已收货 4已退款 5已完成',
create_date datetime DEFAULT null comment '订单的创建时间',
pay_date datetime DEFAULT null comment '支付时间',
PRIMARY key(id)
)ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET = utf8mb4;

create table t_seckill_goods(
id bigint(20) not null auto_increment comment '秒杀商品id',
goods_id bigint(20) default null comment '商品id',
seckill_price decimal(10,2) DEFAULT '0.00' comment '秒杀价格',
stock_count int(10) DEFAULT null comment '库存数量',
start_date datetime DEFAULT null comment '开始时间',
end_date datetime DEFAULT null comment '结束时间',
PRIMARY key(id)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET = utf8mb4;


create table t_seckill_order(
id bigint(20) not null auto_increment comment '秒杀订单id',
user_id bigint(20) DEFAULT null comment '用户id',
order_id BIGINT(20) DEFAULT NULL COMMENT '订单id',
goods_id BIGINT(20) DEFAULT null COMMENT '商品id',
PRIMARY key(id)
)ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET = utf8mb4;

	
	



	
	


