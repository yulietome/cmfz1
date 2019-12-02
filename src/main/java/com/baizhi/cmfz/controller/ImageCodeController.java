package com.baizhi.cmfz.controller;


import com.baizhi.cmfz.util.ImageCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/imageCode")
public class ImageCodeController {
    @RequestMapping("/getImgCode")
    public void getImgCode(HttpServletRequest request, HttpServletResponse response, String d) throws IOException {
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/png");
        String securityCode = ImageCodeUtil.getSecurityCode(4,ImageCodeUtil.SecurityCodeLevel.Medium,false);
        HttpSession session = request.getSession();
        session.setAttribute("securityCode",securityCode);
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        ImageIO.write(image,"png",response.getOutputStream());
//        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
//        HttpSession session = request.getSession();
//        session.setAttribute("verifyCode",verifyCode);
//        BufferedImage image = VerifyCodeUtil.getImage(180, 30, verifyCode);
//        ImageIO.write(image,"png",response.getOutputStream());

    }
    @RequestMapping(value = "/matchVerificationCode" ,method = RequestMethod.POST)
    @ResponseBody
    public Boolean matchVerificationCode(@RequestBody String verifyCode, HttpSession session){
        String securityCode = (String) session.getAttribute("securityCode");
        String[] strings = verifyCode.split("=");
        verifyCode=strings[1];
        System.out.println(verifyCode);
        return securityCode.equals(verifyCode);
    }
}
