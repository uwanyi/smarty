package com.uwanyi.lottery_draw.service.impl;

import com.uwanyi.lottery_draw.model.SysPermission;
import com.uwanyi.lottery_draw.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by jasonwang
 * on 2019/9/7 14:53
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    /*@Autowired
    private SysPermissionMapper permissionMapper;*/

    /**
     * 获取指定角色所有权限
     */
    @Override
    public List<SysPermission> listByRoleId(Integer roleId) {
        return new ArrayList<>();
    }
}
