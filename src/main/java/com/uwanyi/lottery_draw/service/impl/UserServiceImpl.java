package com.uwanyi.lottery_draw.service.impl;

import com.uwanyi.lottery_draw.model.User;
import com.uwanyi.lottery_draw.mapper.UserMapper;
import com.uwanyi.lottery_draw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author jasonwang
 * @since 2020-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
