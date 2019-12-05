package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private String id;

    private String title;


    private String coverSrc;

    private String author;

    private String beam;

    private String score;

    private Integer count;

    private Date publishDate;

    private String content;

}