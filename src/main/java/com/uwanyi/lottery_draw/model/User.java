package com.uwanyi.lottery_draw.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表 
 * </p>
 *
 * @author jasonwang
 * @since 2020-08-01
 */
@ApiModel(value = "user",description = "用户信息表")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends Model {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信号id,或者其他用户名 唯一
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "QQ号")
    private String qq;

    @ApiModelProperty(value = "微信号")
    private String wx;

    /**
     * 1块钱等于10U币
     */
    @ApiModelProperty(value = "总金额",notes = "总金额")
    private Long uTotal;

    @ApiModelProperty(value = "可用金额",notes = "可用金额")
    private Long uEnableTotal;

    @ApiModelProperty(value = "冻结金额",notes = "冻结金额")
    private Long uDisableTotal;

    /**
     * 1: 普通用户   2：vip（会员）用户   3：超级vip用户
            以后可根据用户的增加设置不同的等级类型 默认为1
     */
    @ApiModelProperty(value = "会员等级：1: 普通用户 2：vip（会员）用户 3：超级vip用户")
    private Integer vipType;

    @ApiModelProperty(value = "用户头像")
    private String photo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 0 : 不活跃   1：活跃  默认为1，创建用户时默认活跃用户
            定时器判断为1的用户最后一次登录时间若超过一个月表示非活跃用户，
            用户再次访问再设置为活跃用户
     */
    @ApiModelProperty(value = "是否活跃用户 0 : 不活跃 1：活跃")
    private Integer isActiveUser;


}
