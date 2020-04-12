package com.staff.common.pojo;

import java.io.Serializable;

/**
 * @author 
 */
public class WorkStaffTable implements Serializable {
    /**
     * 员工编号
     */
    private Long staffId;

    /**
     * 岗位编号
     */
    private Long workId;

    /**
     * 数据状态
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}