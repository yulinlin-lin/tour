package com.sgu.tourism.Dto;


import lombok.*;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class THotelOrder {

  /**
   * 这个对象是前面传递过来的数据；
   * 前台按照规定来填写数据后，就会形成数据 ；
   */

  private long hotelOrderId;
  private String userName;
  private String userPhone;
  private String idNumber;
  private String userSex;
  private String userNote;
  private String hotelId;
  private long orderNumbers;
  private long totalPrice;
  private String canBack;
  private String liveDate;
  private String leaveDate;
  private long orderUserId;
  private String createTime;
  private long status;



}
