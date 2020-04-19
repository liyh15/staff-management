package com.staff.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 */
@Data
public class SalaryTable implements Serializable {

    private String staffName;

    /**
     * 薪资编号
     */
    private Long salaryId;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 员工编号
     */
    private String staffCount;

    /**
     * 0:无效
            1:有效
     */
    private String status;

    /**
     * 工资数量
     */
    private Integer salaryNum;

    /**
     * 发放类型，0：现金 1：打卡
     */
    private String type;

    /**
     * 工资备注
     */
    private String commont;

    private static final long serialVersionUID = 1L;

}