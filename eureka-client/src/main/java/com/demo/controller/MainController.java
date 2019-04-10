package com.demo.controller;

import com.demo.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 主要页面控制层
 */
@Controller
public class MainController {

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            return "/login";
        }
        return "/index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "/register";
    }
}
