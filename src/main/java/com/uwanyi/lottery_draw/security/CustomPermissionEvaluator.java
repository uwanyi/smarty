package com.uwanyi.lottery_draw.security;

import com.uwanyi.lottery_draw.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.DenyAllPermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * created by jasonwang
 * on 2019/9/7 15:43
 * 注册自定义权限验证规则，本类不需要在CustomSecurityConfig里面被@Bean，新的版本已经帮我们自动做了，
 * 如果再加入的话，就会报错。下面这段看看就行了，不用加入使用。
 * @Bean
public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
handler.setPermissionEvaluator(customPermissionEvaluator);
return handler;
}*/
@Component
public class CustomPermissionEvaluator extends DenyAllPermissionEvaluator {
    @Autowired
    private SysPermissionService permissionService;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获得loadUserByUsername()方法的结果
        User user = (User)authentication.getPrincipal();
        // 获得loadUserByUsername()中注入的角色
        Collection<GrantedAuthority> authorities = user.getAuthorities();

        // 遍历用户所有角色
        for(GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            //简易版的验证，GrantedAuthority这里面存放的是权限
            if(roleName.equals(permission)) return true;

            /*Integer roleId = roleService.selectByName(roleName).getId();
            // 得到角色所有的权限
            List<SysPermission> permissionList = permissionService.listByRoleId(roleId);*/
            //此处先写出固定的，用于搭建框架测试 begin
            /*List<SysPermission> permissionList = new ArrayList<>();
            SysPermission sysPermission2 = new SysPermission();
            sysPermission2.setPermission("SEARCHUSER,ADDUSER,UPDATEUSER");
            permissionList.add(sysPermission2);
            // end
            // 遍历permissionList
            for(SysPermission sysPermission : permissionList) {
                // 获取权限集
                List permissions = sysPermission.getPermissions();
                // 如果访问的Url和权限用户符合的话，返回true
                if(targetUrl.equals(sysPermission.getUrl())
                        && permissions.contains(permission)) {
                    return true;
                }
            }*/

        }

        return false;
    }
}
