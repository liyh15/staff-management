package com.staff.server.mapper;

import com.staff.common.pojo.RoleStaffTable;

public interface RoleStaffTableMapper {
    int insert(RoleStaffTable record);

    int insertSelective(RoleStaffTable record);
}