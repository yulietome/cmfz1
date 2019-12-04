package com.baizhi.cmfz.serviceimpl;

import com.baizhi.cmfz.dao.UserMapper;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.entity.UserExample;
import com.baizhi.cmfz.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> showAllUsers(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userMapper.selectByRowBounds(new User(), new RowBounds((page - 1) * rows, rows));
        Integer count = userMapper.selectCount(new User());
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", total);
        map.put("record", count);
        map.put("page", page);
        map.put("rows", users);
        return map;
    }

    @Override
    public void changeUserStatus(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(user, userExample);
    }
}
