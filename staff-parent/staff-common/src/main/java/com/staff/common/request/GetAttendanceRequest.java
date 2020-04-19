package com.staff.common.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAttendanceRequest {

    private Integer pageNo;

    private LocalDate date;

    private String status;

    private String staffCount;
}
