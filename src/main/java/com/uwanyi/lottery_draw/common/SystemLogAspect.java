package com.uwanyi.lottery_draw.common;


import com.uwanyi.lottery_draw.model.SystemLog;
import com.uwanyi.lottery_draw.model.User;
import com.uwanyi.lottery_draw.model.common.ResultBean;
import com.uwanyi.lottery_draw.model.common.ReturnCode;
import com.uwanyi.lottery_draw.service.SystemLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * created by jasonwang
 * on 2019/7/20 0:17
 */
@Aspect
@Component
@Order(-5)
public class SystemLogAspect {

    @Autowired
    private SystemLogService systemLogService;

    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    @Pointcut("@annotation(com.uwanyi.lottery_draw.common.SystemControllerLog)")
    public void controllerAspect(){}

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public ResultBean recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        SystemLog systemLog = new SystemLog();
        Object proceed = null ;
        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User)request.getSession().getAttribute("loginUser");
        systemLog.setUserId(user.getUsername());
        //获取请求的ip
        String ip = request.getRemoteAddr();
        systemLog.setRequestIp(ip);
        //获取执行的方法名
        systemLog.setActionmethod(joinPoint.getSignature().getName());

        //获取方法执行前时间
        Date date=new Date();
        systemLog.setActiondate(date);

        proceed = joinPoint.proceed();
        //提取controller中ExecutionResult的属性
        ResultBean result = (ResultBean) proceed;

        if (result.getStatus().equals(ReturnCode.SUCCESS)){
            //设置操作信息
            systemLog.setIsSuccess(1);
        }else{
            systemLog.setIsSuccess(2);
        }
        //获取执行方法的注解内容
        systemLog.setDescription(getControllerMethodDescription(joinPoint)+":"+result.getMessage());
        Object[] params = joinPoint.getArgs() ;
        String returnStr = "" ;
        for (Object param : params) {
            if (param instanceof String){
                returnStr+= param ;
            }else if (param instanceof Integer){
                returnStr+= param ;
            }
        }
        systemLog.setParams(returnStr);

        systemLogService.insert(systemLog);

        return result ;
    }

    //异常处理
    @AfterThrowing(pointcut = "controllerAspect()",throwing="e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable{
        SystemLog systemLog = new SystemLog();
        Object proceed = null ;
        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User)request.getSession().getAttribute("loginUser");
        systemLog.setUserId(user.getUsername());
        //获取请求的ip
        String ip = request.getRemoteAddr();
        systemLog.setRequestIp(ip);
        systemLog.setIsSuccess(2);
        systemLog.setActionmethod(joinPoint.getSignature().getName());
        systemLog.setDescription(e.getClass().getName()+","+e.getMessage());
        systemLogService.insert(systemLog);
    }


    /***
     * 获取controller的操作信息
     * @param point
     * @return
     */
    public String getControllerMethodDescription(ProceedingJoinPoint point) throws  Exception{
        //获取连接点目标类名
        String targetName = point.getTarget().getClass().getName() ;
        //获取连接点签名的方法名
        String methodName = point.getSignature().getName() ;
        //获取连接点参数
        Object[] args = point.getArgs() ;
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods() ;
        String description="" ;
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length){
                    description = method.getAnnotation(SystemControllerLog.class).descrption();
                    break;
                }
            }
        }
        return description ;
    }
}