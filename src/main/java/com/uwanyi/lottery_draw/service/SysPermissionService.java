package com.uwanyi.lottery_draw.service;


import com.uwanyi.lottery_draw.model.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> listByRoleId(Integer roleId);
}
