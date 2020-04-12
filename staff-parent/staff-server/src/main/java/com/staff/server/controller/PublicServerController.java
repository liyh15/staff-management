package com.staff.server.controller;

import com.staff.common.config.BusinessException;
import com.staff.common.config.CodeUtil;
import com.staff.common.config.ErrorCode;
import com.staff.common.pojo.StaffTable;
import com.staff.common.request.LoginRequest;
import com.staff.common.response.BaseResponse;
import com.staff.server.mapper.StaffTableMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/public/server")
public class PublicServerController {

    @Autowired
    private StaffTableMapper staffTableMapper;

    /**
     * 用户登录
     * @return
     */
    @RequestMapping(value = "/login",consumes = "application/json;charset=UTF-8",
                    produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public BaseResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) throws InterruptedException {
         StaffTable staffTable = staffTableMapper.selectByCount(loginRequest.getCount());
         if (null == staffTable) {
            BusinessException.throwException(ErrorCode.Status.COUNT_PASS_ERROR);
         }
         HttpSession session = request.getSession();
         String confirmCode = (String) session.getAttribute("confirmCode");
         // 验证验证码
         if (!loginRequest.getConfirmCode().toUpperCase().equals(confirmCode)) {
             BusinessException.throwException(ErrorCode.Status.CONFIRM_CODE_ERROR);
         }
         // 验证密码
         String password = staffTable.getStaffPassword();
         if (!password.equals(MD5(loginRequest.getPassword()))) {
             BusinessException.throwException(ErrorCode.Status.COUNT_PASS_ERROR);
         }
         // 登录成功
         session.setAttribute("isLogin", "yes");
         return BaseResponse.DEFAULT;
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/getConfirmCode")
    public void getConfirmCode(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        Map<String, Object> map = CodeUtil.generateCodeAndPic();
        HttpSession session = request.getSession();
        session.setAttribute("confirmCode", map.get("code").toString());
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", response.getOutputStream());
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/aaa")
    public String aaa(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String aaa = (String) session.getAttribute("confirmCode");
        return aaa;
    }


    /**
     * MD5加密算法
     */
    private String MD5(String password) {
        // 给初始密码添加密钥，并进行初始加密
        String md5Str = DigestUtils.md5Hex(password + "staffPassword!@#$%@!");
        for (int i = 0; i < 10 ; i++) {
            // 进行十次轮询加密
            md5Str = DigestUtils.md5Hex(md5Str + "staffPassword!@#$%@!" + i);
        }
        return md5Str;
    }
}
