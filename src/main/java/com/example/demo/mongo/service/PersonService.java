package com.example.demo.mongo.service;

import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.bean.PersonGroup;

import java.util.Collection;
import java.util.List;

/**
 * 查询人员接口
 */
public interface PersonService {


    /**
     * 查询出所有的人员
     * @return
     */
    List<Person> findAllPerson();


    /**
     * 将从mysql中查询到person数据保存至mongo中
     * @return
     */
    Collection<Person> savePersonToMongo();


    /**
     * 将mysql中查询到person处理好保存至mongo中
     * @return
     */
    Collection<PersonGroup> savePersonGroupToMongo();


}
