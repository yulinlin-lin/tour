package com.sgu.tourism.mapper;


import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.HotelCommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ITHotelInfoMapper extends BaseDao<THotelInfo> {


    List<THotelInfo> findAllHotels(@Param("page") int page,@Param("limit") Integer limit,@Param("key") String key);

    int getCount(@Param("key") String key);

    int deleteSceneryById(Integer hid);

    THotelInfo queryHotelById(Integer sid);

    int updateActiveStatus(@Param("id")Integer id,@Param("status") Integer status);

    List<THotelInfo> findAllTHotels();

    List<THotelInfo> getSceneryByType(Integer hotelType);

    List<ChartsVo> getHotelNames();

    List<HotelCommentsVo> findHotelCommentVos(@Param("hotelId") Integer hotelId,
                                              @Param("startIndex") int startIndex);

}
