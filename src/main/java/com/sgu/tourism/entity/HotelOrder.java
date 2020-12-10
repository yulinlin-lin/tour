package com.sgu.tourism.entity;

import lombok.*;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HotelOrder {

  private Integer hotelOrderId;
  private String userName;
  private String userPhone;
  private String idNumber;
  private String userSex;
  private String userNote;


//  入住时间
  private String liveTime;
//  单价
  private String onePrice;

  private String hotelTitle;
  private Integer orderNumbers;
  private Integer totalPrice;
  private String orderUserName;  //这个属性从session中获取
  private String createTime;  //在系统里面获取 ；

//  逻辑删除；
  private Integer del;




}
