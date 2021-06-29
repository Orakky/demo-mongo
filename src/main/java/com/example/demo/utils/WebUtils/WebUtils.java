package com.example.demo.utils.WebUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * web相关工具类
 */
public class WebUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    /**
     * 响应json数据推送给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response,Object obj){
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            //java对象转为json格式的数据（objectMapper.writerValueAsString)
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            //内容推送到客户端浏览器
            response.flushBuffer();
        }catch (Exception e){
            LOGGER.info("json数据推送给前端发生异常:{}",e.getMessage());
        }
    }

}
