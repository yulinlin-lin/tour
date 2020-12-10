package com.sgu.tourism.vo;

import com.sgu.tourism.entity.Comment;
import lombok.*;

/**
 * @author huang
 * @date 2020/11/19 15:11
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo extends Comment {
    private String userName;
}
