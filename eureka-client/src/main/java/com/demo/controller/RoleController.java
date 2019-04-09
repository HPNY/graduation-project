package com.demo.controller;

import com.demo.entity.Role;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role/add")
    @ResponseBody
    public String addRole(Role role) {
        roleService.addRole(role);
        return "success";
    }

    @RequestMapping("/role/role_update")
    @ResponseBody
    public String updateRole(Role role) {
        roleService.updateRole(role);
        return "success";
    }

    @RequestMapping("/role/findone")
    public String findOneRole(Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "/role/role_update";
    }

    @RequestMapping("/role/delete")
    @ResponseBody
    public String delRole(Integer id) {
        roleService.delRole(id);
        return "success";
    }

    @RequestMapping("/role/role_list")
    public String findAllRole(Integer pageCount, Model model) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Role> page = roleService.findAllRoleByPage(pageCount);
        model.addAttribute("page", page);
        Integer total = page.getTotalPages();
        model.addAttribute("pageCount", pageCount + 1);
        model.addAttribute("total", total);
        return "/role/role_list";
    }

    @RequestMapping("/role/containing")
    public String findAllByNameContaining(Integer pageCount, String name, Model model) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Role> page = roleService.findAllByNameContaining(name, pageCount);
        model.addAttribute("page", page);
        Integer total = page.getTotalPages();
        model.addAttribute("pageCount", pageCount + 1);
        model.addAttribute("total", total);
        return "/role/role_list";
    }

    @RequestMapping("/role/role_add")
    public String addPage() {
        return "/role/role_add";
    }

    @ResponseBody
    @RequestMapping("/role/list")
    public Page findAll(Integer pageCount, String containing) {
        Page page;
        if (containing == null) {
            page = roleService.findAllRoleByPage(pageCount);
        } else {
            page = roleService.findAllByNameContaining(containing, pageCount);
        }
        return page;
    }


}
