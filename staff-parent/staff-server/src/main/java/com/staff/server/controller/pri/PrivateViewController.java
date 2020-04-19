package com.staff.server.controller.pri;

import com.staff.common.config.CookieUtil;
import com.staff.common.pojo.WorkStaffTable;
import com.staff.server.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/private/view")
public class PrivateViewController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StaffTableMapper staffTableMapper;

    @Autowired
    private WorkTableMapper workTableMapper;

    @Autowired
    private WorkStaffTableMapper workStaffTableMapper;

    @Autowired(required = true)
    private SalaryTableMapper salaryTableMapper;

    @Autowired
    private AttendanceTableMapper attendanceTableMapper;

    /**
     * 用户对应的主页
     * @return
     */
    @RequestMapping("/mainView")
    public String mainView(HttpServletRequest request) {
        String staffCount = CookieUtil.getCookieByName(request,"count");
        WorkStaffTable workStaffTable = workStaffTableMapper.selectByCount(staffCount);
        if (workStaffTable.getWorkId() == 1000L) {
            return "index";
        } else if (workStaffTable.getWorkId() == 1001L) {
            return "indexAdmin";
        }
        return "indexNormal";
    }
}
