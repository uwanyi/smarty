package com.uwanyi.lottery_draw.model.common;

import java.util.Collection;

/**
 * 当使用@ResponseBody返回数据时使用此类，统一规范管理
 * 可参考 MytestController
 * @param <T>
 */
public class ResultBean<T> {
    /**
     * 状态码，参见：ExceptionCode
     */
    private int code;
    /**
     * 成功：true, 失败：false
     */
    private Boolean status;
    /**
     * 错误信息描述
     */
    private String message;
    /**
     * 成功时返回的集合对象
     */
    private Collection<T> data;

    private ResultBean() {

    }

    public static ResultBean error(int code, String message) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        resultBean.setStatus(ReturnCode.FAIL);
        return resultBean;
    }

    public static ResultBean success() {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ExceptionCode.SUCCESS);
        resultBean.setMessage("success");
        resultBean.setStatus(ReturnCode.SUCCESS);
        return resultBean;
    }

    public static <V> ResultBean<V> success(Collection<V> data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ExceptionCode.SUCCESS);
        resultBean.setMessage("success");
        resultBean.setData(data);
        resultBean.setStatus(ReturnCode.SUCCESS);
        return resultBean;
    }

    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    private void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Collection<T> getData() {
        return data;
    }

    private void setData(Collection<T> data) {
        this.data = data;
    }
}
