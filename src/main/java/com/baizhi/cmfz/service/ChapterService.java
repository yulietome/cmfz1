package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ChapterService {
    String addChapter(Chapter chapter);

    void uploadChapter(MultipartFile filePath, String id, String albumId, HttpServletRequest request);

    Map<String, Object> showAllChapter(String albumId, Integer page, Integer rows);

    void chapterUpdate(Chapter chapter);

    void delete(Chapter chapter);
    void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response);
}
