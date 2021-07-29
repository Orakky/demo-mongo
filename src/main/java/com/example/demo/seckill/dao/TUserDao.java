package com.example.demo.seckill.dao;

import com.example.demo.seckill.bean.TUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TUserDao {


    TUser selectById(String id);
}