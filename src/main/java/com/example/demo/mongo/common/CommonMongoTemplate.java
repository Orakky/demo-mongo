package com.example.demo.mongo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 编写mongodb 分页查询工具
 */
@Repository
public class CommonMongoTemplate {


    @Autowired
    private MongoTemplate mongoTemplate;


    public <T> PageImpl<T> page(Pageable pageable, Class<T> clazz, Criteria criteriaWhere){
        Query query = Query.query(criteriaWhere);

        //每页5个
        query.with(pageable);
        int count = (int) mongoTemplate.count(query,clazz);
        List<T> items = mongoTemplate.find(query,clazz);
        return new PageImpl<>(items,pageable,count);
    }
}
