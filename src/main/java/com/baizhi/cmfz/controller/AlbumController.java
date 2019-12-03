package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @RequestMapping("edit")
    public String edit(Album album,String oper){
        String id = null;
        //编辑
        if (oper.equals("edit")) {
            albumService.albumUpdate(album);
        }
        //添加
        if (oper.equals("add")){
            id = albumService.addAlbum(album);
        }
        //删除
        if (oper.equals("del")) {
            albumService.delete(album);
        }
        return id;
    }

    @RequestMapping("uploadAlbumCover")
    public void uploadAlbumCover(MultipartFile coverSrc, String id, HttpServletRequest request) {
        albumService.uploadAlbumCover(coverSrc, id, request);
    }

    @RequestMapping("showAllAlbum")
    @ResponseBody
    public Map<String, Object> showAllAlbum(Integer page, Integer rows) {
        Map<String, Object> map = albumService.showAllAlbum(page, rows);
        return map;
    }
}
