package com.sgu.tourism.service;

import com.sgu.tourism.entity.Comment;
import com.sgu.tourism.vo.CommentVo;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/19 14:58
 */
public interface ICommentService {

    List<CommentVo> findAllCOmments(Integer page, Integer limit, String key);


    int getCount(String key);

    int deleteCommentById(Integer id);

    boolean addComment(Comment comment);

}
