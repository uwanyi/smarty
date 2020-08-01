package com.uwanyi.lottery_draw.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义用户信息服务类
 * created by jasonwang
 * on 2019/9/7 15:33
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户的用户名: {}", username);
        //在此处可写自己的逻辑，可与数据库关联，查找该用户的信息，若用户不存在则抛出异常
        // TODO 根据用户名，查找到对应的密码，与权限
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        //String password = "123456";
        log.info("用户的密码: {}", password);
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User("wjs040", password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("SEARCHUSER,ADDUSER,UPDATEUSER"));
        return user;
    }
}
