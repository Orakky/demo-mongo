package com.example.demo.register.handler;

import com.example.demo.utils.WebUtils.JsonData;
import com.example.demo.utils.WebUtils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 未登录处理器
 */
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        WebUtils.sendJsonMessage(response, JsonData.buildError("请登录!!"));

    }
}
