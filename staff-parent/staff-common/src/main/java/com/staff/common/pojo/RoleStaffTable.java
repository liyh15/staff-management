package com.staff.common.pojo;

import java.io.Serializable;

/**
 * @author 
 */
public class RoleStaffTable implements Serializable {
    /**
     * 用户编号
     */
    private Long staffId;

    /**
     * 角色编号
     */
    private Long roleId;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}