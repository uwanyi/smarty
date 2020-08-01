package com.uwanyi.lottery_draw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemLog {
    /**
     * 操作用户
     */
    private String userId;
    /**
     * 请求的ip地址
     */
    private String requestIp;
    /**
     * 执行的方法
     */
    private String actionmethod;
    /**
     * 请求的参数
     */
    private String params;
    /**
     * 执行时间
     */
    private Date actiondate;
    /**
     * 是否成功  1：成功  2：失败
     */
    private Integer isSuccess;
    /**
     * 描述
     */
    private String description;

}
