package com.example.demo.mongo.service;

import com.example.demo.mongo.bean.BaseCondition;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.bean.PersonGroup;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    /**
     * 根据条件+分页查询mongodb
     * @param condition
     * @return
     * fixme
     */
    List<Person> queryPeron(BaseCondition condition);


    /**
     * 利用mongoTemplate封装好的分页工具查询
     * @param pageable
     * @param condition
     * @return
     */
    PageImpl<Person> personList(Pageable pageable, BaseCondition condition);


    /**
     * mongo查询列表，实现分页查询
     * @param condition
     * @return
     */
    PageImpl<PersonGroup> personGroupList(Pageable pageable,BaseCondition condition);



}
