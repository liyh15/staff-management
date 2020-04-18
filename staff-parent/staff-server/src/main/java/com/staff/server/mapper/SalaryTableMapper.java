package com.staff.server.mapper;

import com.staff.common.pojo.SalaryTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryTableMapper {
    int deleteByPrimaryKey(Long salaryId);

    int insert(SalaryTable record);

    int insertSelective(SalaryTable record);

    SalaryTable selectByPrimaryKey(Long salaryId);

    int updateByPrimaryKeySelective(SalaryTable record);

    int updateByPrimaryKey(SalaryTable record);

    List<SalaryTable> selectSalaryByParam(@Param("staffCount") String staffCount,
                                          @Param("year") String year,
                                          @Param("month") String month);
}