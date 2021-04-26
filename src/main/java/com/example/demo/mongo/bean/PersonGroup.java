package com.example.demo.mongo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

/**
 * 分组后并组装过的bean
 */
@Document
public class PersonGroup {



    @Id
    private String id;

    private String name;

    private int age;

    private int telTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTelTotal() {
        return telTotal;
    }

    public void setTelTotal(int telTotal) {
        this.telTotal = telTotal;
    }
}
