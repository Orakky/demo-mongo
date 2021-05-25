package com.example.demo.aspect;

import com.example.demo.annotation.CheckParams;
import org.springframework.stereotype.Component;

@Component
public class Check {

    @CheckParams
    public void check(String a){
        System.out.println("a:");
    }
}
