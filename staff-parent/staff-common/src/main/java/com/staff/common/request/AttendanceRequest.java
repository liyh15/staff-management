package com.staff.common.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AttendanceRequest {

    private String status;

    private String commont;
}
