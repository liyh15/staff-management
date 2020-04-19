package com.staff.server.mapper;

import com.staff.common.dto.GetStaffDTO;
import com.staff.common.pojo.StaffTable;
import com.staff.common.pojo.WorkTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffTableMapper {
    int deleteByPrimaryKey(Long staffId);

    int insert(StaffTable record);

    int insertSelective(StaffTable record);

    StaffTable selectByPrimaryKey(Long staffId);

    int updateByPrimaryKeySelective(StaffTable record);

    int updateByPrimaryKey(StaffTable record);

    StaffTable selectByCount(@Param("count") String count);

    List<GetStaffDTO> selectByName(@Param("name") String name, Integer pageNo);

    List<StaffTable> selectAll();

    Integer selectCountByName(@Param("name") String name);

    GetStaffDTO selectByStaffCount(@Param("staffCount") String staffCount);
}