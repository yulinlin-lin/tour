package com.sgu.tourism.entity;

import lombok.*;
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LineOrder {

  private Integer lineOrderId;
  private Integer userId;   //  要自己从session中拿到；
  private String userName;
  private String userPhone;
  private String idNumber;
  private String userSex;
  private String userNote;
//
  private String lineTitle;
  private String bookingWay;
  private String startTime;
  private String canBack;
  private String onePrice;
  private Integer number;
  private Integer totalPrice;
  private String createTime;   //在创建的时候要自己添加



// 进行逻辑删除；
  private Integer del;


}
