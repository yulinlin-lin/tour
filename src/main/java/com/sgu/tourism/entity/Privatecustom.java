package com.sgu.tourism.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Privatecustom {
  private Integer id;
  private String origin;
  private String destination;
  private String departureDate;
  private String returnDate;
  private int number;
  private int budget;
  private int line;
  private int airTicket;
  private int car;
  private int guide;
  private int hotel;
  private int visa;
  private int doorTicket;
  private int dining;

  private String userName;
  private String phone;
  private String note;
  private Integer tourismType;
  private String createTime;




}
