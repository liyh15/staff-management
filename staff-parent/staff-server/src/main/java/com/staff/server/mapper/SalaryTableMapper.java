package com.staff.server.mapper;

import com.staff.common.pojo.SalaryTable;

public interface SalaryTableMapper {
    int deleteByPrimaryKey(Long salaryId);

    int insert(SalaryTable record);

    int insertSelective(SalaryTable record);

    SalaryTable selectByPrimaryKey(Long salaryId);

    int updateByPrimaryKeySelective(SalaryTable record);

    int updateByPrimaryKey(SalaryTable record);
}