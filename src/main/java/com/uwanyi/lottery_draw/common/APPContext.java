package com.uwanyi.lottery_draw.common;

import com.uwanyi.lottery_draw.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by jasonwang
 * on 2019/7/20 0:03
 */
@Slf4j
public class APPContext {
    /**
     * 获取当前请求Request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attr == null) {
            log.error("Failed to get the request!");
            throw new NullPointerException("Failed to get the request!");
        }
        return attr.getRequest();
    }



    /**
     * 获取当前请求Session对象
     *
     * @return
     */
    public static HttpSession getSession() {

        return getRequest().getSession();
    }

    /**
     * 设置当前登录用户
     *
     * @param loginInfo
     */
    public static void setLoginInfo(User loginInfo) {

        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute("loginUser", loginInfo);
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getLoginInfo() {

        HttpSession session = getSession();
        if (session != null) {
            return (User) getSession().getAttribute("loginUser");
        }

        return null;
    }

    /**
     * 获取用户是否已经登录
     *
     * @return
     */
    public static boolean isLogin() {

        if (getLoginInfo() == null)
            return false;

        return true;
    }

    /**
     * 退出应用时处理销毁工作
     */
    public static void destroyed() {

        getSession().removeAttribute("loginUser");
    }
    /**
     * 根据参数返回当前session
     */
    public static Object getSessionObject(String sessionName){
        HttpSession session = getSession();
        if (session != null) {
            return  getSession().getAttribute(sessionName);
        }
        return null;
    }
    /**
     * 根据参数设置session
     */
    public static void setSessionObject(String sessionName,Object object){
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(sessionName, object);
        }
    }
}
