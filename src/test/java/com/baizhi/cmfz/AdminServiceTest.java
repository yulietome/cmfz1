package com.baizhi.cmfz;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    AdminService adminService;
    @Test
    public void loginTest(){
        Admin admin= new Admin();
        admin.setPassword("admin");
        admin.setUsername("admin");
        Admin login = adminService.login(admin);
        System.out.println(login);
    }

}
