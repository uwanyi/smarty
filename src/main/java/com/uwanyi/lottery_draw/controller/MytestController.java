package com.uwanyi.lottery_draw.controller;

import com.uwanyi.lottery_draw.common.SystemControllerLog;
import com.uwanyi.lottery_draw.model.User;
import com.uwanyi.lottery_draw.model.common.CustomException;
import com.uwanyi.lottery_draw.model.common.ResultBean;
import com.uwanyi.lottery_draw.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by jasonwang
 * on 2020/8/1 12:06
 */
@Slf4j
@Controller
public class MytestController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login() throws CustomException {
        log.info("进入登录页");
        return "login";
    }
    /**
     * security成功后执行的url,必须是post的请求
     * @return
     */
    @PostMapping("/auth/index")
    public String index(){
        System.out.println("进入首页：index");
        return "index";
    }

    /**
     * 不是跳转页面，必须加@ResponseBody
     * @return
     * @throws CustomException
     */
    @GetMapping("/getMytest")
    @SystemControllerLog(descrption = "查询用户信息",actionType = "4")
    @ResponseBody
    public ResultBean<User> getMytestInfo() throws CustomException {
        return ResultBean.success( userService.findAllUser());

    }
    @GetMapping("/auth/search/user")
    @PreAuthorize("hasPermission('/user','SEARCHUSER')")
    public String searchUser(ModelMap modelMap) throws CustomException {
       /* SysUser sysUser = new SysUser();
        sysUser.setUserCode("1001");
        APPContext.setLoginInfo(sysUser);*/
        log.info("/auth/search/user");
        modelMap.put("user","/auth/search/user");
        return "index";
    }
}
