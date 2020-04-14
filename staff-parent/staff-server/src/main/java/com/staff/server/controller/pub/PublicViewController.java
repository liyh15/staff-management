package com.staff.server.controller.pub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一公共页面访问接口
 */
@Controller
@RequestMapping("/public/view")
public class PublicViewController {

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/loginView")
    public String loginView() {
        return "login";
    }
}
