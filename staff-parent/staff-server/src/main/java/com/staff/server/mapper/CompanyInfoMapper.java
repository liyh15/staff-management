package com.staff.server.mapper;

import com.staff.common.pojo.CompanyInfo;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Long infoId);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);
}