package com.example.demo.register.bean;

import java.io.Serializable;

public class SysRole implements Serializable {

    static final long serialVerisonUID = 1L;

    private Integer id;

    private String name;

    public static long getSerialVerisonUID() {
        return serialVerisonUID;
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
}
