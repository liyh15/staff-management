package com.staff.server.mapper;

import com.staff.common.pojo.AttendanceTable;

public interface AttendanceTableMapper {
    int deleteByPrimaryKey(Long attendanceId);

    int insert(AttendanceTable record);

    int insertSelective(AttendanceTable record);

    AttendanceTable selectByPrimaryKey(Long attendanceId);

    int updateByPrimaryKeySelective(AttendanceTable record);

    int updateByPrimaryKey(AttendanceTable record);
}