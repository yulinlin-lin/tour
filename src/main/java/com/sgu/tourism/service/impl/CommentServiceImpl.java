package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.Comment;
import com.sgu.tourism.mapper.ICommentMapper;
import com.sgu.tourism.service.ICommentService;
import com.sgu.tourism.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/19 14:59
 */
@Service
public class CommentServiceImpl implements ICommentService {


    @Autowired
    private ICommentMapper iCommentMapper;
    @Override
    public List<CommentVo> findAllCOmments(Integer page, Integer limit, String key) {
        List<CommentVo> vos = iCommentMapper.findAllCommentsByPage((page-1)*limit,limit,key);
        return vos;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = iCommentMapper.getCount(key);
        return count;
    }

    @Override
    public int deleteCommentById(Integer id) {
        int count = iCommentMapper.deleteCommentById(id);
        return count;
    }

    @Override
    public boolean addComment(Comment comment) {
        boolean flag = false;
        Integer integer = iCommentMapper.addComment(comment);

        if (integer >0){
            flag = true;
        }
        return flag;
    }

}
