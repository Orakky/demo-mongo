package com.example.demo.register.service.impl;

import com.example.demo.register.bean.SysRole;
import com.example.demo.register.dao.SysRoleDao;
import com.example.demo.register.service.SysRoleService;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 根据id获取sysRole
     *
     * @param roleId
     * @return
     */
    @Override
    public SysRole selectById(Integer roleId) {

        SysRole sysRole = (SysRole) redisUtil.hashGet("SysRoleService","selectById"+roleId);
        if(sysRole == null){
            sysRole = sysRoleDao.selectById(roleId);
            redisUtil.hashSet("SysRoleService","selectById"+roleId,sysRole,180);
        }

        return sysRole;
    }
}
