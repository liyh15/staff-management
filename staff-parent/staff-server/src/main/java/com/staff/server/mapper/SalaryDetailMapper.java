package com.staff.server.mapper;

import com.staff.common.pojo.SalaryDetail;

public interface SalaryDetailMapper {
    int deleteByPrimaryKey(Long salaryDetailId);

    int insert(SalaryDetail record);

    int insertSelective(SalaryDetail record);

    SalaryDetail selectByPrimaryKey(Long salaryDetailId);

    int updateByPrimaryKeySelective(SalaryDetail record);

    int updateByPrimaryKey(SalaryDetail record);
}