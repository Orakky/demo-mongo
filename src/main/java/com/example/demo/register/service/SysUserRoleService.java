package com.example.demo.register.service;


import com.example.demo.register.bean.SysUserRole;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * sysUserRoleService
 */
@Service
public interface SysUserRoleService {

    /**
     * 根据user.id获取user的权限
     * @param id
     * @return
     */
    List<SysUserRole> listByUserId(Integer id);
}
