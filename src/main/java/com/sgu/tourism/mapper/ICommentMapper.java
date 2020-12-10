package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.Comment;
import com.sgu.tourism.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/19 14:59
 */
@Mapper
public interface ICommentMapper extends BaseDao<Comment> {

    List<CommentVo> findAllCommentsByPage(@Param("page") int page,
                                          @Param("limit") Integer limit,
                                          @Param("key") String key);

    int getCount(@Param("key")String key);

    int deleteCommentById(Integer id);

    Integer addComment(Comment comment);

}
