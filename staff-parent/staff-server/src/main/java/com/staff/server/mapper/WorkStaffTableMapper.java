package com.staff.server.mapper;
import com.staff.common.pojo.WorkStaffTable;
import org.apache.ibatis.annotations.Param;

public interface WorkStaffTableMapper {
    int insert(WorkStaffTable record);

    int insertSelective(WorkStaffTable record);

    int deleteWorkStaff(@Param("staffCount") String staffCount);

    WorkStaffTable selectByCount(@Param("staffCount") String staffCount);
}