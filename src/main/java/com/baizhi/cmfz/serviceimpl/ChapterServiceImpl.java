package com.baizhi.cmfz.serviceimpl;
import com.baizhi.cmfz.dao.ChapterMapper;
import com.baizhi.cmfz.entity.Chapter;
import com.baizhi.cmfz.entity.ChapterExample;
import com.baizhi.cmfz.service.AlbumService;
import com.baizhi.cmfz.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private AlbumService albumService;

    @Override
    public String addChapter(Chapter chapter) {
        String id = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(id);
        chapter.setStatus("1");
        chapterMapper.insertSelective(chapter);
        albumService.addCount(chapter.getAlbumId());
        return id;
    }

    @Override
    public void uploadChapter(MultipartFile filePath, String id, String albumId, HttpServletRequest request) {
        System.out.println(id);
        String realPath = new String("target/classes/static/upload/chapter/");
        File file = new File(realPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        String filename = filePath.getOriginalFilename();
        String newFilename = new Date().getTime() + "-" + filename;
        try {
            System.out.println(file.getAbsolutePath());
            File file2 = new File(file.getAbsolutePath() + File.separator + newFilename);
            filePath.transferTo(file2);
            MP3File mp3File = new MP3File(file2);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            String duration = audioHeader.getTrackLengthAsString();
            System.out.println(duration);
            Long size = filePath.getSize();
            double v = size / 1024 / 1024;
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setDuration(duration);
            chapter.setSize(v);
            chapter.setFilename(newFilename);
            chapter.setAlbumId(albumId);
            ChapterExample chapterExample = new ChapterExample();
            chapterExample.createCriteria().andIdEqualTo(chapter.getId());
            chapterExample.createCriteria().andAlbumIdEqualTo(chapter.getAlbumId());
            chapterMapper.updateByExampleSelective(chapter, chapterExample);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> showAllChapter(String albumId, Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andAlbumIdEqualTo(albumId);
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        Integer count = chapterMapper.selectCountByExample(chapterExample);
        Integer total = count % rows != 0 ? count / rows + 1 : count / rows;
        map.put("total", total);
        map.put("records", count);
        map.put("page", page);
        map.put("rows", chapters);
        return map;
    }

    @Override
    public void chapterUpdate(Chapter chapter) {
        if (chapter.getFilename().equals("")) {
            chapter.setFilename(null);
        }
        ChapterExample example = new ChapterExample();
        example.createCriteria().andIdEqualTo(chapter.getId());
        chapterMapper.updateByExampleSelective(chapter, example);
    }

    @Override
    public void delete(Chapter chapter) {
        ChapterExample example = new ChapterExample();
        example.createCriteria().andIdEqualTo(chapter.getId());
        chapterMapper.deleteByExample(example);
        albumService.discount(chapter.getAlbumId());
    }

    @Override
    public void audioDownload(String audioName, HttpServletRequest request, HttpServletResponse response) {
        String realPath = new String("target/classes/static/upload/chapter/");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(realPath, audioName));
            response.setHeader("content-disposition", "attachment;fileName=" +
                    URLEncoder.encode(audioName, "UTF-8"));
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
