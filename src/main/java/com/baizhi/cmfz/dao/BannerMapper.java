package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.entity.BannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BannerMapper extends Mapper<Banner> {

}