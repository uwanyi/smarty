package com.uwanyi.lottery_draw.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 自定义验证用户名密码的逻辑
 * 该类未在此框架系统中使用，只作为参考，因为security提供的验证规则已经够我们使用了
 * //若使用的话 去掉注释，然后在配置类CustomSecurityConfig中的方法中加入：
 *  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.authenticationProvider(new CustomAuthenticationProvider());
    }
 * created by jasonwang
 * on 2019/9/7 15:36
 */
//@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        CustomAuthenticationProvider customAuthenticationProvider = new CustomAuthenticationProvider();
        PasswordEncoder passwordEncoder = customAuthenticationProvider.getPasswordEncoder();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }
    //这个方法一定也要重写哦，就是把里面的注释点即可。不然启动都会报错，
    protected void doAfterPropertiesSet() throws Exception {
        //Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }
}
