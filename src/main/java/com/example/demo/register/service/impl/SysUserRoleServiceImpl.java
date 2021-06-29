package com.example.demo.register.service.impl;

import com.example.demo.register.bean.SysUserRole;
import com.example.demo.register.dao.SysUserRoleDao;
import com.example.demo.register.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * sysUserRoleServiceImpl
 */
public class SysUserRoleServiceImpl implements SysUserRoleService {


    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    /**
     * 根据user.id获取user的权限
     *
     * @param id
     * @return
     */
    @Override
    public List<SysUserRole> listByUserId(Integer id) {
        return sysUserRoleDao.listByUserId(id);
    }
}
