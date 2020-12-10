package com.sgu.tourism.entity;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TLineInfo {

  private long lineId;
  private String startCity;
  private String endCity;
  private String lineTitle;
  private String lineIntroduce;

  private String lineSup;
  private String linePrice;
  private String lineOldPrice;
  private String startDate;
  private long traveDays;

  private long canBack;
  private String note;
  private String createTime;
  private long active;
  private long lineType;
  private String imgFileName;




}
