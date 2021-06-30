package com.example.demo.register.handler;

import com.example.demo.utils.WebUtils.JsonData;
import com.example.demo.utils.WebUtils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足管理器
 */
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        WebUtils.sendJsonMessage(response, JsonData.buildError("权限不够，请联系管理员!"));
    }
}
