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

    /**
     * 登录界面
     * @return
     */
    @RequestMapping("/left")
    public String left() {
        return "left";
    }

    /**
     * 用户对应的主页
     * @return
     */
    @RequestMapping("/mainView")
    public String mainView() {
        return "index";
    }

    /**
     * 用户对应的主页
     * @return
     */
    @RequestMapping("/mainfra")
    public String mainfra() {
        return "mainfra";
    }

    /**
     * 用户对应的主页
     * @return
     */
    @RequestMapping("/top")
    public String top() {
        return "top";
    }
}
