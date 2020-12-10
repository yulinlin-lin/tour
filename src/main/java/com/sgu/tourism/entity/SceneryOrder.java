package com.sgu.tourism.entity;


import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SceneryOrder {

  private Integer sceneryOrderId;
  private String userName;
  private String userPhone;
  private String idNumber;
  private String userSex;
  private String userNote;

  private String userDate;

  private String sceneryName;
  private String ticketType;
  private String sceneryPrice;
  private String sceneryNumber;

  private String totalPrice;
  private String orderUserName;

  private String createTime;

  private Integer del;



}
