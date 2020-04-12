package com.staff.server.mapper;

import com.staff.common.pojo.RoleTable;

public interface RoleTableMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleTable record);

    int insertSelective(RoleTable record);

    RoleTable selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RoleTable record);

    int updateByPrimaryKey(RoleTable record);
}