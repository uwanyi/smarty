package com.uwanyi.lottery_draw.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类
 * created by jasonwang
 * on 2019/7/24 10:14
 * @Data、@NoArgsConstructor、@AllArgsConstructor、@Accessors(chain=true)这些注解，都是lombok帮助处理set/get和全参无参构造方法的
 * @NoArgsConstructor  无参构造
 * @AllArgsConstructor 全参构造
 * @Data 数据字段的get/set
 * @Accessors(chain=true)链式写法  obj.setId(1).setName("wang")...
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String url;

    private Integer roleId;

    private String permission;

    private List permissions;

    /*public List getPermissions() {
        return Arrays.asList(this.permission.trim().split(","));
    }

    public void setPermissions(List permissions) {
        this.permissions = permissions;
    }*/

}