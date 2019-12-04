package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.AlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface AlbumMapper extends Mapper<Album> {
    void addCount(String albumId);

    void discount(String albumId);
}