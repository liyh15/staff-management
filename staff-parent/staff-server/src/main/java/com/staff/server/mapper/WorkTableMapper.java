package com.staff.server.mapper;

import com.staff.common.pojo.WorkTable;

import java.util.List;

public interface WorkTableMapper {
    int deleteByPrimaryKey(Long workId);

    int insert(WorkTable record);

    int insertSelective(WorkTable record);

    WorkTable selectByPrimaryKey(Long workId);

    int updateByPrimaryKeySelective(WorkTable record);

    int updateByPrimaryKey(WorkTable record);

    List<WorkTable> selectAll();

}