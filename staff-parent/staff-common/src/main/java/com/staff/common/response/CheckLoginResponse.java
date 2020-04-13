package com.staff.common.response;

import lombok.Data;

@Data
public class CheckLoginResponse extends BaseResponse<CheckLoginResponse> {

    // 是否登录，0:否  1:是
    private String isLogin;
}
