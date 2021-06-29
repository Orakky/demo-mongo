package com.example.demo.register.dao;

import com.example.demo.register.bean.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleDao {

    /**
     * 根据role.id查询权限角色
     * @param roleId
     * @return
     */
    SysRole selectById(Integer roleId);
}


