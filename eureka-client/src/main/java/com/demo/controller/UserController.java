package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/user/user_add")
    @ResponseBody
    public String addUser(User user) {
        user = userService.addUser(user);
        if (user == null) {
            return "userExist";
        }
        return "success";
    }

    @RequestMapping("/user/user_update")
    @ResponseBody
    public String updateUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        user = userService.findById(user.getId());
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUser(user);
        return "success";
    }

    @RequestMapping("/user/findone")
    public String findoneUser(Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/user/user_update";
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public String delUser(Integer id) {
        userService.delUser(id);
        return "success";
    }

    @RequestMapping("/user/user_list")
    public String findAllUser() {
        return "/user/user_list";
    }

    @RequestMapping("/user/containing")
    public String findAllByUsernameContaining(Integer pageCount, String username, Model model) {
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

    @RequestMapping("/message")
    public String message(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public String updateRole(String[] role,Integer idNo) {
//        String[] roles = request.getParameterValues("role");
//        Integer userId = Integer.parseInt(request.getParameter("idNo"));
        User user = userService.findById(idNo);
        List list = new ArrayList();
        for (String temp : role) {
            Integer id = Integer.parseInt(temp);
            list.add(roleService.findById(id));
        }
        user.setAuthorities(list);
        userService.updateUser(user);
        return "/user/user_list";
    }

    @RequestMapping("/user/getuser")
    @ResponseBody
    public Integer getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
