package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @ClassName CheckParamsAspect
 * @DESCRIPTION 实现checkparams 参数校验注解的aop切面
 * @Author Orakky
 * @Date 2021/5/9 8:54 下午
 * @Version 1.0
 **/
@Aspect
@Component
public class CheckParamsAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger("CheckParamsAspect.class");

    @Pointcut("@annotation(com.example.demo.annotation.CheckParams)")
    public void annotationPointCut(){
    }


   /**
    *@Description 实现checkParams注解
    *@Author Orakky
    *@Date 2021/5/9 9:15 下午
    *@Param [joinPoint]
    *@return void
    **/
    @Around("annotationPointCut()")
    public void doAround(ProceedingJoinPoint joinPoint){
        LOGGER.info("进入切面，开始检查参数是否符合标准");
        long t1 = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取参数名称
        String[] params = methodSignature.getParameterNames();
        //获取参数值
        Object[] args = joinPoint.getArgs();
        //如果参数为空 或者 参数为null
        if(null == params || params.length == 0){
                LOGGER.info("参数格式校验不正确！");
        }else{
            LOGGER.info("参数格式校验成功！");
        }
        long t2 = System.currentTimeMillis();
        LOGGER.info("检查参数结束，耗时{}ms",t2-t1);
    }



}
