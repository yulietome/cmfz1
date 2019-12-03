package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @RequestMapping("edit")
    public String edit(Album album,String oper){
        String id = null;
        //编辑
        if (oper.equals("edit")){}
        //添加
        if (oper.equals("add")){
            id = albumService.addAlbum(album);
        }
        //删除
        if (oper.equals("del")){}
        return id;
    }

}
