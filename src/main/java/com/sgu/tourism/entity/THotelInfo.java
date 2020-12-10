package com.sgu.tourism.entity;

import lombok.*;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class THotelInfo {

  private Integer hotelId;
  private String hotelLocation;
  private String hotelName;
  private String hotelPrice;
  private String hotelOldPrice;
  private String hotelDetailed;
  private String imgFileName;
  private Integer htype;
  private String tel;
  private Integer status;
  private String createTime;

  private double lng;
  private double lat;

}
