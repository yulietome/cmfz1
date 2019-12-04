package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.entity.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

}