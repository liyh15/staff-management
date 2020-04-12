package com.staff.server.mapper;

import com.staff.common.pojo.WorkStaffTable;

public interface WorkStaffTableMapper {
    int insert(WorkStaffTable record);

    int insertSelective(WorkStaffTable record);
}