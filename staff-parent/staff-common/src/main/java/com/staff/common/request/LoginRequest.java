package com.staff.common.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录接口入参
 */
@Data
public class LoginRequest {

    /**
     * 账号
     */
    @NotBlank(message = "账号为空")
    private String count;

    /**
     * 密码
     */
    @NotBlank(message = "密码为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码为空")
    private String confirmCode;
}
