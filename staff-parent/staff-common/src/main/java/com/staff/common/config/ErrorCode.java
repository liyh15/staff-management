package com.staff.common.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorCode {
    public enum Status {

        SUCESS("200", "成功"),
        CONFIRM_CODE_ERROR("401", "验证码错误"),
        NO_LOGIN("402", "用户登录已过期，请重新登录"),
        USER_NOT_EXIST("403", "用户不存在"),
        USER_IS_EXIST("407", "用户已存在"),
        PARAM_IS_NULL("408", "参数不能为空"),
        USER_IS_ERROR("405", "工资必须是合法数字"),
        ERROR("601", "系统异常"),
        USER_HAS_SALARY("406","用户当年当月已经录入了工资"),
        COUNT_PASS_ERROR("404", "账号或密码错误"),
        // 这里自定义异常
        MESSAGE_IS_NULL("201", "数据为空");

        public String code;
        public String message;

        Status(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
