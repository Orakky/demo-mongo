package com.example.demo.register.service.impl;

import com.example.demo.register.bean.SysUserRole;
import com.example.demo.register.dao.SysUserRoleDao;
import com.example.demo.register.service.SysUserRoleService;
import com.example.demo.utils.RedisUtils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * sysUserRoleServiceImpl
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {


    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据user.id获取user的权限
     *
     * @param id
     * @return
     */
    @Override
    public List<SysUserRole> listByUserId(Integer id) {
        List<SysUserRole> sysUserRoleList = (List<SysUserRole>) redisUtil.hashGet("SysUserRoleService","listByUserId" + id);
        if(sysUserRoleList == null){
            sysUserRoleList = sysUserRoleDao.listByUserId(id);
            redisUtil.hashSet("SysUserRoleService","listByUserId"+id,sysUserRoleList,180);
        }

        return sysUserRoleList;
    }
}
