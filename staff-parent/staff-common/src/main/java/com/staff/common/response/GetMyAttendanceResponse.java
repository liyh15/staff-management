package com.staff.common.response;

import com.staff.common.pojo.AttendanceTable;
import lombok.Data;

@Data
public class GetMyAttendanceResponse extends BaseResponse<GetMyAttendanceResponse> {

    private AttendanceTable attendanceTable;
}
