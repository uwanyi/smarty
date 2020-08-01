package com.uwanyi.lottery_draw.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义输出异常
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String message;

}
