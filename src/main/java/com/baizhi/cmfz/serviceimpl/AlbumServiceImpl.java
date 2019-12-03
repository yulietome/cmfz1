package com.baizhi.cmfz.serviceimpl;
import com.baizhi.cmfz.dao.AlbumMapper;
import com.baizhi.cmfz.entity.Album;
import com.baizhi.cmfz.entity.AlbumExample;
import com.baizhi.cmfz.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

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

    @Override
    public void uploadAlbumCover(MultipartFile coverSrc, String id, HttpServletRequest request) {
        String realPath = new String("target/classes/static/upload/albumCover/");
        File file = new File(realPath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        String filename = coverSrc.getOriginalFilename();
        String newFilename = new Date().getTime() + "-" + filename;
        try {
            System.out.println(file.getAbsolutePath());
            coverSrc.transferTo(new File(file.getAbsolutePath() + File.separator + newFilename));
            Album album = new Album();
            album.setId(id);
            album.setCoverSrc(newFilename);
            AlbumExample example = new AlbumExample();
            example.createCriteria().andIdEqualTo(album.getId());
            albumMapper.updateByExampleSelective(album, example);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> showAllAlbum(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> albums = albumMapper.selectByRowBounds(new Album(), rowBounds);
        Integer count = albumMapper.selectCount(new Album());
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", total);
        map.put("records", count);
        map.put("page", page);
        map.put("rows", albums);
        return map;
    }

    @Override
    public void albumUpdate(Album album) {
        if (album.getCoverSrc() == "") {
            album.setCoverSrc(null);
        }
        AlbumExample example = new AlbumExample();
        example.createCriteria().andIdEqualTo(album.getId());
        albumMapper.updateByExampleSelective(album, example);
    }

    @Override
    public void delete(Album album) {
        AlbumExample example = new AlbumExample();
        example.createCriteria().andIdEqualTo(album.getId());
        albumMapper.deleteByExample(example);
    }
}
