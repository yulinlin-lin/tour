package com.sgu.tourism.vo;

import lombok.*;

/**
 * @author huang
 * @date 2020/11/20 10:39
 */
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SceneryCommentsVo {
    private long userId;
    private String userName;
    private String userRealName;
    private String userImgFileName;

    private long commentId;
    private String content;
    private String commentCreateTime;

}
