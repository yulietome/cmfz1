package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @RequestMapping("edit")
    public String edit(Banner banner,String oper){
        String id = null;
        if (oper.equals("add")){
            System.out.println(banner);
            bannerService.addBanner(banner);
            id = banner.getId();
        }
        if (oper.equals("edit")){}
        if (oper.equals("del")){}
        return id;
    }
    @RequestMapping("uploadBanner")
    public void uploadBanner(MultipartFile imgSrc, String id, HttpServletRequest request){
        System.out.println(imgSrc.getSize());
        System.out.println(id);
        bannerService.bannerUpload(imgSrc,id,request);
    }
    @RequestMapping("showAllBanners")
    @ResponseBody
    public Map<String,Object> showAllBanners(Integer page,Integer rows){
        Map<String, Object> stringObjectMap = bannerService.showAllBanner(page, rows);
        return stringObjectMap;
    }

}
