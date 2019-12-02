package com.baizhi.cmfz.serviceimpl;

import com.baizhi.cmfz.dao.AdminMapper;
import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.entity.AdminExample;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminExample adminExample;
    @Override
    public Admin login(Admin admin) {
        //添加查询条件
        adminExample.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.isEmpty()){
            return null;
        }else{
            if (admins.get(0).getPassword().equals(admin.getPassword())){
                return admins.get(0);
            }else {
                return null;
            }
        }

    }
}
