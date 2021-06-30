package com.example.demo.register.handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销处理器
 */
@Component
public class AuthenticationLogout implements LogoutSuccessHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationLogout.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        String token = request.getHeader("token");
        if(token == null){
            token = request.getParameter("token");
        }

        try{
            if(token == null){
                //token为空表示未登录


            }
        }catch (Exception e){
            LOGGER.info("注销认证发生异常:{}",e.getMessage());
        }

    }
}
