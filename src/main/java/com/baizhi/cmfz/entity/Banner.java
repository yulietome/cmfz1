package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    private String id;
    private String imgSrc;
    private String status;
    private String description;
    private Date uploadTime;
}