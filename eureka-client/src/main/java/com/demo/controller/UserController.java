package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户相关控制层
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 用户注册
     * 成功返回登录页面
     * 失败返回注册界面并显示账号重名
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/user_add")
    public String addUser(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        user = userService.addUser(user);
        if (user == null) {
            model.addAttribute("userExist", true);
            return "/register";
        }
        return "/login";
    }


    /**
     * 更新用户并返回列表页
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/user/user_update")
    public String updateUser(User user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        user = userService.findById(user.getId());
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUser(user);
        return findAllUser(0, model);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/user/findone")
    public String findoneUser(Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user/user_update";
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/user/delete")
    public String delUser(Integer id, Model model) {
        userService.delUser(id);
        return findAllUser(0, model);
    }

    /**
     * 显示所有用户
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/user_list")
    public String findAllUser(Integer pageCount, Model model) {
//        List<User> list = userService.findAllUser();
//        model.addAttribute("list", list);
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<User> page = userService.findAllUser(pageCount);
        model.addAttribute("page", page);
        Integer total = page.getTotalPages();
        model.addAttribute("pageCount", pageCount + 1);
        model.addAttribute("total", total);
        return "/user/user_list";
    }

    /**
     * 根据用户输入字段模糊查找用户名包含字段的用户
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping("/user/containing")
    public String findAllByUsernameContaining(Integer pageCount, String username, Model model) {
//        List<User> list = userService.findAllByUsernameContaining(username);
//        model.addAttribute("list", list);
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<User> page = userService.findAllByUsernameContaining(username, pageCount);
        model.addAttribute("page", page);
        Integer total = page.getTotalPages();
        model.addAttribute("pageCount", pageCount + 1);
        model.addAttribute("total", total);
        return "/user/user_list";
    }

    /**
     * 个人信息界面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/message")
    public String message(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/message";
    }

    @ResponseBody
    @RequestMapping("/user/list")
    public Page findAll(Integer pageCount, String containing) {
        Page page;
        if (containing == null) {
            page = userService.findAllUser(pageCount);
        } else {
            page = userService.findAllByUsernameContaining(containing, pageCount);
        }
        return page;
    }

    @ResponseBody
    @RequestMapping("/user/user_role")
    public Collection selectRole(Integer id) {
        Collection collection = userService.findById(id).getAuthorities();
        return collection;
    }

    @RequestMapping("/user/role")
    public String role(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/user_role";
    }

    @RequestMapping("/user/user")
    public String updateRole(HttpServletRequest request, Model model) {
        String[] roles = request.getParameterValues("role");
        Integer userId = Integer.parseInt(request.getParameter("idNo"));
        User user = userService.findById(userId);
        List list = new ArrayList();
        for (String temp : roles) {
            Integer id = Integer.parseInt(temp);
            list.add(roleService.findById(id));
        }
        user.setAuthorities(list);
        userService.updateUser(user);
        return findAllUser(0, model);
    }
}
