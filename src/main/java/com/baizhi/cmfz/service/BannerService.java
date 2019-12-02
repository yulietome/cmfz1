package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BannerService {
    String addBanner(Banner banner);
    void bannerUpload(MultipartFile imgSrc, String id, HttpServletRequest request);
}
