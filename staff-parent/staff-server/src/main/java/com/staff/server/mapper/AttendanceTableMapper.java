package com.staff.server.mapper;

import com.staff.common.pojo.AttendanceTable;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceTableMapper {
    int deleteByPrimaryKey(Long attendanceId);

    int insert(AttendanceTable record);

    int insertSelective(AttendanceTable record);

    AttendanceTable selectByPrimaryKey(Long attendanceId);

    int updateByPrimaryKeySelective(AttendanceTable record);

    int updateByPrimaryKey(AttendanceTable record);

    Integer selectCount(@Param("date") LocalDate date, @Param("status") String status,
                        @Param("staffCount") String staffCount);

    List<AttendanceTable> selectByParams(@Param("date") LocalDate date, @Param("status") String status,
                                         @Param("staffCount") String staffCount,@Param("pageNo") Integer pageNo);
}