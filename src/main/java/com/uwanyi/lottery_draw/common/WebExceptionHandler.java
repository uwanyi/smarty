package com.uwanyi.lottery_draw.common;

/**
 * created by jasonwang
 * on 2019/7/20 0:25
 */
import com.uwanyi.lottery_draw.model.common.CustomException;
import com.uwanyi.lottery_draw.model.common.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常统一处理机制
 * 规定：
 * 明确异常时抛出：CustomException，并加上ExceptionCode里面的编码，没有请自定义一个，以便于管理
 * 未知的异常：Exception
 */

/**
 * /**
 * 官方异常写法，可供参考，精确到每一个异常，而且统一抛出异常，然后交给异常类处理
 * try {
 listeners.started(context);
 callRunners(context, applicationArguments);
 }
 catch (Throwable ex) {
 handleRunFailure(context, ex, exceptionReporters, listeners);
 throw new IllegalStateException(ex);
 }

 try {
 listeners.running(context);
 }
 catch (Throwable ex) {
 handleRunFailure(context, ex, exceptionReporters, null);
 throw new IllegalStateException(ex);
 }
 */
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler
    public ResultBean incorrectCredentials(CustomException e) {
        log.error("错误码："+e.getCode() +",错误信息：" + e.getMessage());
        return ResultBean.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler
    public ResultBean unknownException(Exception e) {
        log.error("未知异常信息", e);
        // 发送邮件通知技术人员.
        return ResultBean.error(-999, "系统出现错误, 请联系网站管理员!");
    }
}
