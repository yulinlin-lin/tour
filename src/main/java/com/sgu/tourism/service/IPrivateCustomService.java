package com.sgu.tourism.service;

import com.sgu.tourism.entity.Privatecustom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/29 13:25
 */
public interface IPrivateCustomService {

    int addPrivateCustom(Privatecustom privatecustom);

    List<Privatecustom> queryAllPirvateCustoms(Integer page, Integer limit, String key);

    int getCount(String key);

    int updatePrivateCourtomById(Privatecustom privatecustom);

    int deleteByPrivateId(Integer id);

}
