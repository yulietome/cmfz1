package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.service.ChapterService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("edit")
    public String edit(Chapter chapter, String oper) {
        String id = null;
        System.out.println(chapter);
        //编辑
        if (oper.equals("edit")) {
            chapterService.chapterUpdate(chapter);
        }
        //添加
        if (oper.equals("add")) {
            id = chapterService.addChapter(chapter);
        }
        //删除
        if (oper.equals("del")) {
            chapterService.delete(chapter);
        }
        return id;
    }

    @RequestMapping("chapterUpload")
    public void chapterUpload(MultipartFile filename, String id, String albumId, HttpServletRequest request) {
        System.out.println(albumId);
        chapterService.uploadChapter(filename, id, albumId, request);
    }

    @RequestMapping("showAllChapter")
    public Map<String, Object> showAllChapter(String albumId, Integer page, Integer rows) {
        return chapterService.showAllChapter(albumId, page, rows);
    }

    @RequestMapping("audioDownload")
    public void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response) {
        chapterService.audioDownload(audioName, request, response);
    }
}
