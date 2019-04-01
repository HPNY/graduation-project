package com.demo.controller;

import com.demo.entity.Role;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色页面控制层
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增权限角色
     *
     * @param role
     * @param model
     * @return
     */
    @RequestMapping("/role/add")
    public String addRole(Role role, Model model) {
        roleService.addRole(role);
        return findAllRole(0, model);
    }

    /**
     * 修改权限角色信息
     *
     * @param role
     * @param model
     * @return
     */
    @RequestMapping("/role/role_update")
    public String updateRole(Role role, Model model) {
        roleService.updateRole(role);
        return findAllRole(0, model);
    }

    @RequestMapping("/role/findone")
    public String findOneRole(Integer id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "/role/role_update";
    }

    /**
     * 删除权限角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/role/delete")
    public String delRole(Integer id, Model model) {
        roleService.delRole(id);
        return findAllRole(0, model);
    }

    /**
     * 显示所有权限角色
     *
     * @param model
     * @return
     */
    @RequestMapping("/role/role_list")
    public String findAllRole(Integer pageCount, Model model) {
//        List<Role> list = roleService.findAllRole();
//        model.addAttribute("list", list);
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

    /**
     * 根据用户输入字段模糊查找权限名包含字段的角色
     *
     * @param name
     * @param model
     * @return
     */
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

//    @RequestMapping("/role/next")
//    public String next(Page page,Model model){
//        page.nextPageable();
//    }


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
