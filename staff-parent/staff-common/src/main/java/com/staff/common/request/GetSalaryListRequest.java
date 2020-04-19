package com.staff.common.request;

import lombok.Data;

@Data
public class GetSalaryListRequest {

    private Integer pageNo;

    private String staffCount;

    private String year;

    private String month;
}
