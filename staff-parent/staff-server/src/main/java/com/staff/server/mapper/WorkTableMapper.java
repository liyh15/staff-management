package com.staff.server.mapper;

import com.staff.common.pojo.WorkTable;

public interface WorkTableMapper {
    int deleteByPrimaryKey(Long workId);

    int insert(WorkTable record);

    int insertSelective(WorkTable record);

    WorkTable selectByPrimaryKey(Long workId);

    int updateByPrimaryKeySelective(WorkTable record);

    int updateByPrimaryKey(WorkTable record);
}