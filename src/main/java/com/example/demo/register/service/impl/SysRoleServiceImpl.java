package com.example.demo.register.service.impl;

import com.example.demo.register.bean.SysRole;
import com.example.demo.register.dao.SysRoleDao;
import com.example.demo.register.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;


public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    private SysRoleDao sysRoleDao;


    /**
     * 根据id获取sysRole
     *
     * @param roleId
     * @return
     */
    @Override
    public SysRole selectById(Integer roleId) {
        return sysRoleDao.selectById(roleId);
    }
}
