package com.baizhi.cmfz.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("OrderNumGenerator")
public class OrderNumGenerator {
    public String getOrderNum(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        return format;
    }
}
