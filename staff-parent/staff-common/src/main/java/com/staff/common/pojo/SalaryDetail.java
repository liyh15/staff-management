package com.staff.common.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 */
public class SalaryDetail implements Serializable {
    /**
     * 薪资详情编号
     */
    private Long salaryDetailId;

    /**
     * 薪资编号
     */
    private Long salaryId;

    /**
     * 详情内容
     */
    private String detailContent;

    /**
     * 金额
     */
    private Integer salartNum;

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

    private static final long serialVersionUID = 1L;

    public Long getSalaryDetailId() {
        return salaryDetailId;
    }

    public void setSalaryDetailId(Long salaryDetailId) {
        this.salaryDetailId = salaryDetailId;
    }

    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public Integer getSalartNum() {
        return salartNum;
    }

    public void setSalartNum(Integer salartNum) {
        this.salartNum = salartNum;
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
}