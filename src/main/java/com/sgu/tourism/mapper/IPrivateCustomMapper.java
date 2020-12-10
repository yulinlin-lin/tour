package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.Privatecustom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/29 13:27
 */
@Mapper
public interface IPrivateCustomMapper extends BaseDao<Privatecustom> {


    List<Privatecustom> queryAllPirvateCustoms(@Param("page")int page,
                                               @Param("limit") Integer limit,
                                               @Param("key") String key);

    int getCount(@Param("key")String key);

    int deleteByPrivateId(Integer id);

}
