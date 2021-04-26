package com.example.demo.mongo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.service.PersonService;
import com.example.demo.mysql.dao.PersonDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 查询人员接口实现类
 */
@Service
public class PersonServiceImpl implements PersonService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);


    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private PersonDao personDao;

    /**
     * 查询出所有的人员
     *
     * @return
     */
    @Override
    public List<Person> findAllPerson() {
        return mongoTemplate.findAll(Person.class);
    }

    /**
     * 将从mysql中查询到person数据保存至mongo中
     * @return
     */
    @Override
    public Collection<Person> savePersonToMongo() {
        List<Person> dataList = personDao.queryAllPerson();
        Collection<Person> insert = mongoTemplate.insert(dataList, Person.class);

        LOGGER.info("成功插入数据 : {}", JSONObject.toJSONString(insert));
        return insert;
    }


}
