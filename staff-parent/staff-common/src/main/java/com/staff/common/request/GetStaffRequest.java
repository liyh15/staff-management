package com.staff.common.request;

import lombok.Data;

@Data
public class GetStaffRequest {

    // 员工姓名
    private String staffName;

    // 页数
    private Integer pageNo;
}
