package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("showAllUsers")
    public Map<String, Object> showAllUsers(Integer page, Integer rows) {
        return userService.showAllUsers(page, rows);
    }

    @RequestMapping("changeStatus")
    public void changeStatus(User user) {
        userService.changeUserStatus(user);
    }

}
