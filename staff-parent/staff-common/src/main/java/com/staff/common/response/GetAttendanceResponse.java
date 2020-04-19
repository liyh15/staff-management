package com.staff.common.response;

import com.staff.common.pojo.AttendanceTable;
import lombok.Data;

import java.util.List;

@Data
public class GetAttendanceResponse extends BaseResponse<GetAttendanceResponse> {

    private Integer pageTotal;

    private List<AttendanceTable> attendanceTableList;
}
