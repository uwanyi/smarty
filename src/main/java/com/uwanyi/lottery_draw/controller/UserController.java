package com.uwanyi.lottery_draw.controller;


import com.uwanyi.lottery_draw.common.APPContext;
import com.uwanyi.lottery_draw.common.SystemControllerLog;
import com.uwanyi.lottery_draw.model.User;
import com.uwanyi.lottery_draw.model.common.CustomException;
import com.uwanyi.lottery_draw.model.common.ResultBean;
import com.uwanyi.lottery_draw.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author jasonwang
 * @since 2020-08-01
 */
@Api(tags = "用户管理模块处理接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="获取用户详细信息", notes="根据id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/{id}/getUser")
    public User getUser(@PathVariable int id){
        return userService.getById(id);
    }

    @ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }



}
