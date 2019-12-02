package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public String login(Admin admin, HttpSession session){
        System.out.println(admin);
        Admin admin1 = adminService.login(admin);
        if (admin1!=null){
            session.setAttribute("admin",admin1);
            return "Ok";
        }else {
            return "用户名或密码错误";
        }
    }
}
