package com.uwanyi.lottery_draw.service;

import com.uwanyi.lottery_draw.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表  服务类
 * </p>
 *
 * @author jasonwang
 * @since 2020-08-01
 */
public interface IUserService extends IService<User> {
    List<User> findAllUser();
}
