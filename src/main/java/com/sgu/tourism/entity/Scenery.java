package com.sgu.tourism.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Scenery {

    private Integer sceneryId;
    private String sceneryCity;
    private String sceneryTitle;
    private String sceneryPrice;
    private String sceneryOldPrice;

    private String sceneryDetailed1;
    private String sceneryDetailed2;
    private String sceneryDetailed3;
    private String imgFileName;

    private String openTime;
    private Integer sceneryType;
    private String createTime;

    private int del;



}
