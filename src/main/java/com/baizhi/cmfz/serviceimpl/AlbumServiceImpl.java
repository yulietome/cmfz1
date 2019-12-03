package com.baizhi.cmfz.serviceimpl;

import com.baizhi.cmfz.dao.AlbumMapper;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
    @Override
    public String addAlbum(Album album) {
        String id = UUID.randomUUID().toString().replace("-","");
        album.setId(id);
        album.setScore("10");
        album.setCount(0);
        albumMapper.insert(album);
        return id;
    }
}
