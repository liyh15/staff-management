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
     * 员工编号
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
     * 数据状态
     */
    private String status;

    /**
     * 手机
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0：男 1：女
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 描述
     */
    private String describle;

}