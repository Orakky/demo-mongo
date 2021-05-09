package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.CheckParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @ClassName CheckParamsAspect
 * @DESCRIPTION 实现checkparams 参数校验注解的aop切面
 * @Author Orakky
 * @Date 2021/5/9 8:54 下午
 * @Version 1.0
 **/
@Aspect
@Component("checkParamsAspect")
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
    public Object doAround(ProceedingJoinPoint joinPoint){

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        LOGGER.info("进入切面，开始检查参数是否符合标准");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取参数名称
        String[] params = methodSignature.getParameterNames();
        //获取参数值
        Object[] args = joinPoint.getArgs();

        CheckParams annotation =  methodSignature.getMethod().getAnnotation(CheckParams.class);

        String value = annotation.value();

        LOGGER.info("进入自定义注解! {}",value);

        LOGGER.info("参数值:{}", JSONObject.toJSONString(args));


        //如果参数为空 或者 参数为null
        if(null == params || params.length == 0){
                LOGGER.info("参数格式校验不正确！");
                return "参数格式校验不正确！";
        }else{
            LOGGER.info("参数格式校验成功！");
            return "参数校验成功!";
        }

    }



}
