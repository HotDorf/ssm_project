package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public ModelAndView saveRole(Role role){
        ModelAndView mv = new ModelAndView();
        roleService.saveRole(role);
        mv.setViewName("redirect:findAll.do");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView roleById(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.roleById(id);
        mv.addObject("roleShow",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteByRoleid.do")
    public String deleteByRoleid(String id){
        roleService.deleteByRoleid(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPerToRole.do")
    public String addRoleToUser(@RequestParam(name = "roleId",required = true) String roleId,
                                @RequestParam(name = "ids",required = true) String[] permissionIds){
        System.out.println("roleId--"+roleId);
        System.out.println("permissionIds--"+Arrays.toString(permissionIds));

        for (String permissionId : permissionIds) {
            roleService.addPerToRole(roleId,permissionId);
        }
        return "redirect:findAll.do";
    }


}
