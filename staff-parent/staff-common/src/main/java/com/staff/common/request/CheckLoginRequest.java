package com.staff.common.request;

import lombok.Data;

@Data
public class CheckLoginRequest {

    private String count;

    private String sessionId;
}
