package com.staff.server.mapper;

import com.staff.common.pojo.StaffTable;
import org.apache.ibatis.annotations.Param;

public interface StaffTableMapper {
    int deleteByPrimaryKey(Long staffId);

    int insert(StaffTable record);

    int insertSelective(StaffTable record);

    StaffTable selectByPrimaryKey(Long staffId);

    int updateByPrimaryKeySelective(StaffTable record);

    int updateByPrimaryKey(StaffTable record);

    /**
     * 通过账号查找员工
     * @return
     */
    StaffTable selectByCount(@Param("count") String count);
}