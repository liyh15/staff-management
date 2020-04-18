package com.staff.common.request;

import lombok.Data;

@Data
public class PaySalaryRequest {

    private String baseSalary;

    private String otherSalary;

    private String type;

    private String year;

    private String month;

    private String staffCount;

    private String commont;
}
