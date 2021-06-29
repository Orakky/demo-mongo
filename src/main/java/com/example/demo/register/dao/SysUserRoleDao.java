package com.example.demo.register.dao;


import com.example.demo.register.bean.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleDao {


    /**
     * 根据用户id 查询用户权限
     * @param userId
     * @return
     */
    List<SysUserRole> listByUserId(Integer userId);
}
