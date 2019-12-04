package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.User;

import java.util.Map;

public interface UserService {
    Map<String, Object> showAllUsers(Integer page, Integer rows);

    void changeUserStatus(User user);
}
