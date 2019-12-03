package com.baizhi.cmfz;

import com.baizhi.cmfz.dao.AdminMapper;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.AdminExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CmfzApplicationTests {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminExample adminExample;
    @Test
    void contextLoads() {
        List<Admin> admins = adminMapper.selectByRowBounds(new Admin(), new RowBounds(0, 2));
    }
}
