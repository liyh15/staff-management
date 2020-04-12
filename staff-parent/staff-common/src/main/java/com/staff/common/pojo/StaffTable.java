package com.staff.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 */
@Data
public class StaffTable implements Serializable {
    /**
     * 编号
     */
    private Long staffId;

    /**
     * 姓名
     */
    private String staffName;

    /**
     * 身份证号
     */
    private String identityId;

    /**
     * 员工账号
     */
    private String staffCount;

    /**
     * 员工密码
     */
    private String staffPassword;

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
}