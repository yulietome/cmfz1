package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AlbumService {
    String addAlbum(Album album);

    void uploadAlbumCover(MultipartFile coverSrc, String id, HttpServletRequest request);
}
