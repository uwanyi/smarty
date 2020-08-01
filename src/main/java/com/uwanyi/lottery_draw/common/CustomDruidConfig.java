package com.uwanyi.lottery_draw.common;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by jasonwag
 * on 2019/7/18 15:26
 */
@Configuration
public class CustomDruidConfig {
    //配置druid的监控
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //IP白名单
        //servletRegistrationBean.addInitParameter("allow","");
        //IP黑名单
        //servletRegistrationBean.addInitParameter("deny","192.168.20.99");
        //控制台用户
        bean.addInitParameter("loginUsername","admin");
        bean.addInitParameter("loginPassword","123456");
        //是否能够重置数据
        bean.addInitParameter("resetEnable","false");
        return bean;
    }
    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
    /**
     * 注册自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean customFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}