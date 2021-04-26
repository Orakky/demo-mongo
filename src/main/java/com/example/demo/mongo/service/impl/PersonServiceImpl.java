package com.example.demo.mongo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.mongo.bean.Person;
import com.example.demo.mongo.bean.PersonGroup;
import com.example.demo.mongo.service.PersonService;
import com.example.demo.mysql.dao.PersonDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 将mysql中查询到person处理好保存至mongo中
     *
     * @return
     */
    @Override
    public Collection<PersonGroup> savePersonGroupToMongo() {

        List<Person> dataList = personDao.queryAllPerson();
        List<PersonGroup> personGroupList =  personToPersonGroup(dataList);

        long t1 = System.currentTimeMillis();
        Collection<PersonGroup> insert = mongoTemplate.insert(personGroupList, PersonGroup.class);

        long t2 = System.currentTimeMillis();
        LOGGER.info("成功将personGroup插入mongo，耗时{}ms",t2-t1);
        LOGGER.info("成功插入组装后的person数据:{}",JSONObject.toJSONString(insert));
        return insert;

    }

    private List<PersonGroup> personToPersonGroup(List<Person> personList) {



        //根据name分组
        List<PersonGroup> dataList = new ArrayList<>();

        Set<Integer> allAgeSet = new HashSet<>();
        personList.forEach(o->{
            o.setName(o.getName());
            allAgeSet.add(o.getAge());
        });

        //根据name分组
        personList.stream().collect(Collectors.groupingBy((o) -> StringUtils.joinWith("\t", o.getName(), o.getAge()), Collectors.counting()))
                .forEach((v,k)->{
                    final PersonGroup group = new PersonGroup();
                    final String[] split = v.split("\t");
                    group.setName(split[0]);
                    group.setAge(Integer.parseInt(split[1]));
                    group.setTelTotal(Integer.parseInt(String.valueOf(k)));
                    dataList.add(group);
                });

        LOGGER.info("完成分组与组装 ： {}" + JSONObject.toJSONString(dataList));


        return dataList;
    }
}
