package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * 实现自定义注解 对方法的参数进行校验 判断是否为空
 * @Date 2021-05-09
 * @Author Orakky
 *
 * @Target标志作用于method 方法 和type
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParams {


    String value() default "";

}
