package com.staff.common.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 
 */
public class AttendanceTable implements Serializable {
    /**
     * 考勤编号
     */
    private Long attendanceId;

    /**
     * 员工编号
     */
    private String staffCount;

    /**
     * 考勤日期
     */
    private LocalDate attendanceTime;

    /**
     * 0:未签到
            1:已签到
     */
    private String attendanceStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedTime;

    /**
     * 0:无效
            1:有效
     */
    private String status;

    private String staffName;

    /**
     * 说明
     */
    private String commont;

    private static final long serialVersionUID = 1L;

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(String staffCount) {
        this.staffCount = staffCount;
    }

    public LocalDate getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(LocalDate attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCommont() {
        return commont;
    }

    public void setCommont(String commont) {
        this.commont = commont;
    }
}