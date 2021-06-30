package com.example.demo.register.service;

import com.example.demo.register.bean.SysRole;
import org.springframework.stereotype.Service;


/**
 * SysRoleService
 */

public interface SysRoleService {

    /**
     * 根据id获取sysRole
     * @param roleId
     * @return
     */
    SysRole selectById(Integer roleId);
}
