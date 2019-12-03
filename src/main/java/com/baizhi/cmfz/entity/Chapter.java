package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private String id;

    private String filepath;

    private Double size;

    private String duration;

    private String status;

    private String albumId;

}