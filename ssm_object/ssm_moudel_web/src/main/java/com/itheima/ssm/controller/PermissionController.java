package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String savePermission(Permission permission){
        System.out.println(permission);
        if (permission.getPermissionName()!=null&&permission.getUrl()!=null
                &&permission.getPermissionName().length()>0&&permission.getUrl().length()>0) {
            permissionService.savePermission(permission);
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findByPermissionid(String id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findByPermissionid(id);
        mv.addObject("permissionShow",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deleteByPid.do")
    public String deleteByPid(String id){
        permissionService.deleteByPid(id);
        return "redirect:findAll.do";
    }

}
