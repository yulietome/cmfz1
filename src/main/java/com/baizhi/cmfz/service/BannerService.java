package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BannerService {
    String addBanner(Banner banner);
    void bannerUpload(MultipartFile imgSrc, String id, HttpServletRequest request);
    Map<String,Object> showAllBanner(Integer page, Integer rows);
    void bannerUpdate(Banner banner);
    void delete(Banner banner);
}
