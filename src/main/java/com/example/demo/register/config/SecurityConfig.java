package com.example.demo.register.config;


import com.example.demo.register.filter.AuthenticationFilter;
import com.example.demo.register.filter.OncePerRequestAuthoricationFilter;
import com.example.demo.register.handler.AuthenticationLogout;
import com.example.demo.register.handler.TokenAccessDeniedHandler;
import com.example.demo.register.handler.TokenAuthenticationEntryPoint;
import com.example.demo.register.service.impl.UserServiceImpl;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AuthenticationLogout authenticationLogout;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 加密
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserServiceImpl();
    }


    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }


    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权管理
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .and()
                //跨域
                .cors().and()
                //关闭csrf功能：跨站请求伪造 默认只能通过post请求方式提交logout
                .csrf().disable()
                .authorizeRequests()
                //任何请求方式
                .anyRequest().permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessHandler(authenticationLogout)//注销时的逻辑处理
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), redisUtil))   //自定义认证过滤器
                .addFilter(new OncePerRequestAuthoricationFilter(authenticationManager(), redisUtil, (UserServiceImpl) userDetailsService())) //自定义请求过滤器
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     //去除默认的session、cookie
                .and()
                .exceptionHandling().authenticationEntryPoint(new TokenAuthenticationEntryPoint())//未登录时的逻辑处理
                .accessDeniedHandler(new TokenAccessDeniedHandler());    //权限不足时的逻辑处理
    }


    /**
     * 用于解决跨域问题
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
