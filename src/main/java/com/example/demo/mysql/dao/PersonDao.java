package com.example.demo.mysql.dao;

import com.example.demo.mongo.bean.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 读取mysql
 */
@Mapper
public interface PersonDao {

    List<Person> queryAllPerson();
}
