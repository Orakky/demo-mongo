package com.example.demo.mongo.bean;

import java.io.Serializable;

/**
 * 新person实体类
 */
public class NewPerson implements Serializable {

    private String name;

    private int age;

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
}
