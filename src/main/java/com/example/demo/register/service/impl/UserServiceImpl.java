package com.example.demo.register.service.impl;


import com.example.demo.register.bean.SysRole;
import com.example.demo.register.bean.SysUser;
import com.example.demo.register.bean.SysUserRole;
import com.example.demo.register.service.SysRoleService;
import com.example.demo.register.service.SysUserRoleService;
import com.example.demo.register.service.SysUserService;
import com.example.demo.register.service.UserService;
import com.example.demo.utils.RedisUtils.RedisUtil;
import com.example.demo.utils.WebUtils.JsonData;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现UserDetailsService里面的loadUserByUsername()方法 AuthenticationManager会调用此方法去获取用户数据信息，从而完成认证
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查找用户
        SysUser user = sysUserService.selectByName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名出错!");
        }


        //获取用户权限 并将其添加到GrantedAuthority中
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        //添加权限
        List<SysUserRole> userRoles = sysUserRoleService.listByUserId(user.getId());

        for (SysUserRole userRole : userRoles) {
            SysRole role = sysRoleService.selectById(userRole.getRoleId());
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
        }


        //用户名、密码、权限
        return new User(username,user.getPassword(),grantedAuthorityList);
    }


    /**
     * 注册操作
     * @param user
     * @return
     */
    public JsonData register(SysUser user){

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        int insert = sysUserService.insert(user);
        if(insert > 0){
            return JsonData.buildSuccess("注册成功!");
        }else{
            return JsonData.buildError("注册失败!");
        }
    }
}
