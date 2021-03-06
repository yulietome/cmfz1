package com.baizhi.cmfz.serviceimpl;

import com.baizhi.cmfz.dao.BannerMapper;
import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.entity.BannerExample;
import com.baizhi.cmfz.service.BannerService;
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
public class BannerServiceImpl implements BannerService {
    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public String addBanner(Banner banner) {
        String id = UUID.randomUUID().toString().replace("-","");
        banner.setId(id);
        banner.setStatus("1");
        banner.setUploadTime(new Date());
        bannerMapper.insert(banner);
        return id;
    }

    @Override
    public void bannerUpload(MultipartFile imgSrc, String id, HttpServletRequest request) {
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        System.out.println(realPath);
        File file = new File(realPath);
        System.out.println(file.exists());
        if (!file.isDirectory()){
            file.mkdirs();
        }
        String filename = imgSrc.getOriginalFilename();
        String newFilename= new Date().getTime()+"-"+filename;
        try {
            imgSrc.transferTo(new File(file.getAbsolutePath()+File.separator+newFilename));
            Banner banner = new Banner();
            banner.setId(id);
            banner.setImgSrc(newFilename);
            BannerExample example = new BannerExample();
            example.createCriteria().andIdEqualTo(banner.getId());
            bannerMapper.updateByExampleSelective(banner,example);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Map<String,Object> showAllBanner(Integer page, Integer rows) {
        HashMap<String,Object> map= new HashMap<>();
        RowBounds rowBounds = new RowBounds((page-1)*rows,rows);
        List<Banner> banners = bannerMapper.selectByRowBounds(new Banner(), rowBounds);
        Integer count = bannerMapper.selectCount(new Banner());
        Integer total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("records",count);
        map.put("page",page);
        map.put("rows",banners);
        return map;
    }

    @Override
    public void bannerUpdate(Banner banner) {
        if (banner.getImgSrc()==""){
            banner.setImgSrc(null);
        }
        BannerExample example = new BannerExample();
        example.createCriteria().andIdEqualTo(banner.getId());
        bannerMapper.updateByExampleSelective(banner,example);
    }

    @Override
    public void delete(Banner banner) {
        BannerExample example = new BannerExample();
        System.out.println(banner.getId());
        example.createCriteria().andIdEqualTo(banner.getId());
        bannerMapper.deleteByExample(example);
    }
}
