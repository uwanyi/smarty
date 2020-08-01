package com.uwanyi.lottery_draw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * created by jasonwang
 * on 2019/9/7 15:31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()          // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")      // 设置登录页面
                .loginProcessingUrl("/pass/user/login") // 自定义的登录接口
                .successForwardUrl("/auth/index") //该请求必须是post请求
                .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/login","/pass/**","/webjars/**","/css/**", "/js/**", "/fonts/**", "/images/**")
                .permitAll()   // 不需要保护的
                .anyRequest()        // 任何请求,登录后可以访问
                .authenticated()
                .and()
                //.csrf()
                //.disable()
                ;     // 关闭csrf防护 (防护跨域攻击,app服务时一般需要关闭)
        // 开启注销功能
        http.logout()
                .logoutSuccessUrl("/login")
        ;
    }
    /**
     * 密码加密规则，这是默认的，也可以自己定义
     * @return
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //指定自定义的user服务类以及密码加密策略
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**",
                "/favicon.ico"
        );
    }
}
