package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Album;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("chapter")
public class ChapterController {
    @RequestMapping("edit")
    public String edit(Album album, String oper){
        String id = null;
        //编辑
        if (oper.equals("edit")){}
        //添加
        if (oper.equals("add")){}
        //删除
        if (oper.equals("del")){}
        return id;
    }

}
