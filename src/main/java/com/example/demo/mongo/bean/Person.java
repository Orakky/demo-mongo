package com.example.demo.mongo.bean;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "person")//对应集合名称
public class Person  {

    @Id
    private Integer id;

    private String name;

    private int age;

    private String tel;

    private String preffixName;


    public String getPreffixName() {

     return this.name + "ttt";
    }

    public void setPreffixName(String preffixName) {
        this.preffixName = preffixName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
