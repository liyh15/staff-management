package com.staff.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 
 */
@Data
public class WorkStaffTable implements Serializable {
    /**
     * 员工编号
     */
    private String staffCount;

    /**
     * 岗位编号
     */
    private Long workId;

    /**
     * 数据状态
     */
    private String status;

}