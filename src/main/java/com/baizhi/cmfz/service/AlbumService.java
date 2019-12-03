package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AlbumService {
    String addAlbum(Album album);
    void uploadAlbumCover(MultipartFile coverSrc, String id, HttpServletRequest request);

    Map<String, Object> showAllAlbum(Integer page, Integer rows);

    void albumUpdate(Album album);

    void delete(Album album);
}
