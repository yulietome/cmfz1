package com.baizhi.cmfz;

import com.baizhi.cmfz.entity.Banner;
import com.baizhi.cmfz.service.BannerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BannerServiceTest {
    @Autowired
    BannerService bannerService;
    @Test
    public void deleteTest(){
        Banner banner = new Banner();
        banner.setId("416c9cdafedc4f4aac5a3e2fdbca8576");
        bannerService.delete(banner);
    }
}
