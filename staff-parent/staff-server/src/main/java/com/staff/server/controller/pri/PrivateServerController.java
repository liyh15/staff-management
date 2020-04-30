package com.staff.server.controller.pri;

import com.netflix.ribbon.proxy.annotation.Http;
import com.staff.common.config.BusinessException;
import com.staff.common.config.CookieUtil;
import com.staff.common.config.ErrorCode;
import com.staff.common.config.MD5Utils;
import com.staff.common.dto.GetStaffDTO;
import com.staff.common.pojo.*;
import com.staff.common.request.*;
import com.staff.common.response.*;
import com.staff.server.mapper.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/private/system")
public class PrivateServerController {

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


    @RequestMapping(value = "/hello", consumes = {"application/json"}, produces = {"application/json"},
            method = RequestMethod.POST)
    @ApiOperation(value = "hello", notes = "hello", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = String.class)})
    public String hello() {
       return "private hello";
    }

    @RequestMapping(value = "/checkLogin", consumes = {"application/json"}, produces = {"application/json"},
                    method = RequestMethod.POST)
    @ApiOperation(value = "检查是否登录", notes = "检查是否登录", response = CheckLoginResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = CheckLoginResponse.class)})
    public CheckLoginResponse checkLogin(@RequestBody CheckLoginRequest request) {
        CheckLoginResponse response = new CheckLoginResponse();
        String sessionId = redisTemplate.opsForValue().get(request.getCount());
        if (request.getSessionId().equals(sessionId)) {
            response.setIsLogin("1");
            // 刷新用户缓存
            redisTemplate.opsForValue().set(request.getCount(), sessionId, 30, TimeUnit.MINUTES);
        } else {
            response.setIsLogin("0");
        }
        return response;
    }

    /**
     * 注册用户
     * @return
     */
    @RequestMapping(value = "/registeUser",consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    @Transactional
    public BaseResponse registeUser(@RequestBody RegisteRequest registeRequest) {
        StaffTable staff = staffTableMapper.selectByCount(registeRequest.getStaffCount());
        if (null != staff) {
            // 判断用户是否存在
            BusinessException.throwException(ErrorCode.Status.USER_IS_EXIST);
        }
        registeRequest.setCreateTime(LocalDateTime.now());
        registeRequest.setModifiedTime(LocalDateTime.now());
        registeRequest.setStatus("1");
        registeRequest.setStaffPassword(MD5Utils.MD5(registeRequest.getStaffPassword()));
        staffTableMapper.insertSelective(registeRequest);
        Long workId = registeRequest.getWork();
        WorkStaffTable workStaffTable = new WorkStaffTable();
        workStaffTable.setStaffCount(registeRequest.getStaffCount());
        workStaffTable.setWorkId(workId);
        workStaffTable.setStatus("1");
        workStaffTableMapper.insert(workStaffTable);
        return BaseResponse.DEFAULT;
    }

    /**
     * 获取所有的员工
     * @return
     */
    @RequestMapping(value = "/getStaffList", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetStaffListResponse getStaffList(@RequestBody GetStaffRequest getStaffRequest) {
        Integer count = staffTableMapper.selectCountByName(getStaffRequest.getStaffName());
        GetStaffListResponse response = new GetStaffListResponse();
        if (count == 0) {
            response.setPageTotal(1);
            return response;
        } else {
            response.setPageTotal((int)Math.ceil(Double.valueOf(count) / 10));
        }
        List<GetStaffDTO> staffTableList = staffTableMapper.selectByName(getStaffRequest.getStaffName(),
                (getStaffRequest.getPageNo() -1) * 10);
        response.setStaffTableList(staffTableList);
        return response;
    }

    /**
     * 获取所有的考勤记录
     * @return
     */
    @RequestMapping(value = "/getAttendance", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetAttendanceResponse getAttendance(@RequestBody GetAttendanceRequest request) {
        GetAttendanceResponse response = new GetAttendanceResponse();
        Integer count = attendanceTableMapper.selectCount(request.getDate(), request.getStatus(), request.getStaffCount());
        if (count == 0) {
            response.setPageTotal(1);
            return response;
        } else {
            response.setPageTotal((int)Math.ceil(Double.valueOf(count) / 10));
        }
        List<AttendanceTable> attendanceTableList = attendanceTableMapper.
                selectByParams(request.getDate(), request.getStatus(), request.getStaffCount(),
                (request.getPageNo() -1) * 10);
        response.setAttendanceTableList(attendanceTableList);
        return response;
    }

    /**
     * 员工考勤
     * @return
     */
    @RequestMapping(value = "/attandence", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public BaseResponse attandence(@RequestBody AttendanceRequest attendanceRequest, HttpServletRequest request) {
        String staffCount = CookieUtil.getCookieByName(request, "count");
        List<AttendanceTable> attendanceTableList = attendanceTableMapper.
                selectByParams(LocalDate.now(), null, staffCount, 0);
        AttendanceTable attendanceTable = attendanceTableList.get(0);
        attendanceTable.setAttendanceStatus(attendanceRequest.getStatus());
        attendanceTable.setCommont(attendanceRequest.getCommont());
        attendanceTableMapper.updateByPrimaryKeySelective(attendanceTable);
        return BaseResponse.DEFAULT;
    }

    /**
     * 查询员工当日是否已经考勤
     * @return
     */
    @RequestMapping(value = "/getMyAttendance", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetMyAttendanceResponse getMyAttendance(HttpServletRequest request) {
        String staffCount = CookieUtil.getCookieByName(request, "count");
        GetMyAttendanceResponse response = new GetMyAttendanceResponse();
        List<AttendanceTable> attendanceTableList = attendanceTableMapper.
                selectByParams(LocalDate.now(), null, staffCount, 0);
        response.setAttendanceTable(attendanceTableList.get(0));
        return response;
    }

    /**
     * 获取员工工资
     * @return
     */
    @RequestMapping(value = "/getSalaryList", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetSalaryListResponse getSalaryList(@RequestBody GetSalaryListRequest request) {
        Integer count = salaryTableMapper.selectSalryCount(request.getStaffCount(),request.getYear(),request.getMonth());
        GetSalaryListResponse response = new GetSalaryListResponse();
        if (count == 0) {
            response.setPageTotal(1);
            return response;
        } else {
            response.setPageTotal((int)Math.ceil(Double.valueOf(count) / 10));
        }
        List<SalaryTable> salaryTableList = salaryTableMapper.selectSalaryByPageNo(request.getStaffCount(),request.getYear(),request.getMonth(),
                (request.getPageNo() -1) * 10);
        response.setSalaryTableList(salaryTableList);
        return response;
    }

    /**
     * 获取员工工资
     * @return
     */
    @RequestMapping(value = "/getNormalSalaryList", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetSalaryListResponse getNormalSalaryList(@RequestBody GetSalaryListRequest request,
                                                     HttpServletRequest httpServletRequest) {
        String scount = CookieUtil.getCookieByName(httpServletRequest, "count");
        Integer count = salaryTableMapper.selectSalryCount(scount,request.getYear(),request.getMonth());
        GetSalaryListResponse response = new GetSalaryListResponse();
        if (count == 0) {
            response.setPageTotal(1);
            return response;
        } else {
            response.setPageTotal((int)Math.ceil(Double.valueOf(count) / 10));
        }
        List<SalaryTable> salaryTableList = salaryTableMapper.selectSalaryByPageNo(scount,request.getYear(),request.getMonth(),
                (request.getPageNo() -1) * 10);
        response.setSalaryTableList(salaryTableList);
        return response;
    }

    /**
     * 录入工资
     * @return
     */
    @RequestMapping(value = "/paySalary", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public BaseResponse paySalary(@RequestBody PaySalaryRequest request) {
        if (!matchNum(request.getBaseSalary()) || !matchNum(request.getOtherSalary())) {
            BusinessException.throwException(ErrorCode.Status.USER_IS_ERROR);
        }
        // 查询用户是否已经路如果当年当月的工资
        List<SalaryTable> salaryTableList = salaryTableMapper.selectSalaryByParam(request.getStaffCount(),
                request.getYear(),request.getMonth());
        if (!salaryTableList.isEmpty()) {
            BusinessException.throwException(ErrorCode.Status.USER_HAS_SALARY);
        }
        Integer totalSalary = Integer.valueOf(request.getBaseSalary()) + Integer.valueOf(request.getOtherSalary());
        SalaryTable salaryTable = new SalaryTable();
        salaryTable.setCommont(request.getCommont());
        salaryTable.setCreateTime(LocalDateTime.now());
        salaryTable.setModifiedTime(LocalDateTime.now());
        salaryTable.setMonth(request.getMonth());
        salaryTable.setYear(request.getYear());
        salaryTable.setSalaryNum(totalSalary);
        salaryTable.setType(request.getType());
        salaryTable.setStaffCount(request.getStaffCount());
        salaryTable.setStatus("1");
        salaryTableMapper.insertSelective(salaryTable);
        return BaseResponse.DEFAULT;
    }

    /**
     * 获取用户详情
     * @return
     */
    @RequestMapping(value = "/getStaffDetail", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetStaffDetailResponse getStaffDetailResponse(@RequestBody GetStaffDetailRequest request) {
        GetStaffDTO staffDTO = staffTableMapper.selectByStaffCount(request.getStaffCount());
        if (staffDTO == null) {
            BusinessException.throwException(ErrorCode.Status.USER_NOT_EXIST);
        }
        GetStaffDetailResponse response = new GetStaffDetailResponse();
        response.setGetStaffDTO(staffDTO);
        return response;
    }

    /**
     * 获取个人用户详情
     * @return
     */
    @RequestMapping(value = "/getMyStaffDetail", consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public GetStaffDetailResponse getMyStaffDetail(HttpServletRequest request) {
        String staffCount = CookieUtil.getCookieByName(request, "count");
        GetStaffDTO staffDTO = staffTableMapper.selectByStaffCount(staffCount);
        if (staffDTO == null) {
            BusinessException.throwException(ErrorCode.Status.USER_NOT_EXIST);
        }
        GetStaffDetailResponse response = new GetStaffDetailResponse();
        response.setGetStaffDTO(staffDTO);
        return response;
    }

    /**
     * 匹配数字
     * @return
     */
    private boolean matchNum(String str) {
      return Pattern.matches("^[1-9]{1}[0-9]*|0$", str);
    }

    /**
     * 获取所有的职位
     * @return
     */
    @RequestMapping(value = "/getWorkResponse", method = RequestMethod.GET)
    public GetWorkResponse getWorkResponse() {
        List<WorkTable> workTableList = workTableMapper.selectAll();
        salaryTableMapper.selectByPrimaryKey(1L);
        GetWorkResponse response = new GetWorkResponse();
        response.setWorkTableList(workTableList);
        return response;
    }

    /**
     * 注册用户
     * @return
     */
    @RequestMapping(value = "/editUser",consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    @Transactional
    public BaseResponse editUser(@RequestBody RegisteRequest registeRequest) {
        StaffTable staff = staffTableMapper.selectByCount(registeRequest.getStaffCount());
        if (null == staff) {
            // 判断用户是否存在
            BusinessException.throwException(ErrorCode.Status.USER_NOT_EXIST);
        }
        registeRequest.setModifiedTime(LocalDateTime.now());
        registeRequest.setStatus("1");
        if (StringUtils.isNotBlank(registeRequest.getStaffPassword())) {
            registeRequest.setStaffPassword(MD5Utils.MD5(registeRequest.getStaffPassword()));
        }
        registeRequest.setStaffId(staff.getStaffId());
        staffTableMapper.updateByPrimaryKeySelective(registeRequest);
        Long workId = registeRequest.getWork();
        // 先删除用户绑定的职位
        workStaffTableMapper.deleteWorkStaff(registeRequest.getStaffCount());
        WorkStaffTable workStaffTable = new WorkStaffTable();
        workStaffTable.setStaffCount(registeRequest.getStaffCount());
        workStaffTable.setWorkId(workId);
        workStaffTable.setStatus("1");
        workStaffTableMapper.insert(workStaffTable);
        return BaseResponse.DEFAULT;
    }

    /**
     * 编辑岗位
     * @param request
     * @return
     */
    @RequestMapping(value = "/editWork",consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public BaseResponse editWork(@RequestBody EditWorkRequest request) {
         WorkTable workTable = new WorkTable();
         if (StringUtils.isBlank(request.getCommont()) || StringUtils.isBlank(request.getWorkName())) {
             BusinessException.throwException(ErrorCode.Status.PARAM_IS_NULL);
         }
         workTable.setWorkId(Long.valueOf(request.getWorkId()));
         workTable.setWorkName(request.getWorkName());
         workTable.setComment(request.getCommont());
         workTableMapper.updateByPrimaryKeySelective(workTable);
         return BaseResponse.DEFAULT;
    }

    /**
     * 编辑岗位
     * @param request
     * @return
     */
    @RequestMapping(value = "/createWork",consumes = {"application/json"},
            produces = {"application/json"}, method = RequestMethod.POST)
    public BaseResponse createWork(@RequestBody CreateWorkRequest request) {
        WorkTable workTable = new WorkTable();
        workTable.setWorkName(request.getWorkName());
        workTable.setComment(request.getCommont());
        workTable.setCreateTime(LocalDateTime.now());
        workTable.setModifiedTime(LocalDateTime.now());
        workTable.setStatus("1");
        workTableMapper.insertSelective(workTable);
        return BaseResponse.DEFAULT;
    }

    /**
     * 定时生成员工考勤记录
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void attandenceTaskJob() {
        Integer count = attendanceTableMapper.selectCount(LocalDate.now(),null,null);
        if (count == 0) {
            List<StaffTable> staffTableList = staffTableMapper.selectAll();
            if (null != staffTableList && !staffTableList.isEmpty()) {
               for (StaffTable staffTable : staffTableList) {
                   AttendanceTable attendanceTable = new AttendanceTable();
                   attendanceTable.setStaffCount(staffTable.getStaffCount());
                   attendanceTable.setAttendanceTime(LocalDate.now());
                   attendanceTable.setCommont("无");
                   attendanceTable.setAttendanceStatus("未签到");
                   attendanceTable.setStatus("1");
                   attendanceTable.setStaffName(staffTable.getStaffName());
                   attendanceTable.setCreateTime(LocalDateTime.now());
                   attendanceTable.setModifiedTime(LocalDateTime.now());
                   attendanceTableMapper.insertSelective(attendanceTable);
               }
            }
        }
    }
}
