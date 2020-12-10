package com.sgu.tourism.entity;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

  private long id;
  private long userId;
  private long goodsId;
  private String content;
  private long contentType;
  private String createTime;
  private int del;

    public Comment(Integer userid, Integer goodsId, String content, Integer contentType, String nowDateYYYY_mm_dd_2) {
    }



}
