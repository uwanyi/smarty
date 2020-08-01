package com.uwanyi.lottery_draw.mapper;

import com.uwanyi.lottery_draw.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表  Mapper 接口
 * </p>
 *
 * @author jasonwang
 * @since 2020-08-01
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUser();
}
