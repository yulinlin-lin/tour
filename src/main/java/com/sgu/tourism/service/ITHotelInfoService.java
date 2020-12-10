package com.sgu.tourism.service;

import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.HotelCommentsVo;

import java.util.List;

public interface ITHotelInfoService {



    List<THotelInfo> findAllHotels(String page, String limit, String key);
//    查找所有的数据 ；
    List<THotelInfo> findAllHotels();

    int getCount(String key);

    boolean insertHotel(THotelInfo hotel);

    int deleteSceneryById(Integer sid);

    int updateHotelById(THotelInfo hotel);

    THotelInfo queryHotelById(Integer sid);

    boolean updateHotelStatus(Integer id, Integer status);


    List<THotelInfo> getSceneryByType(Integer hotelType);

    List<ChartsVo> getHotelNames();

    List<HotelCommentsVo> findHotelCommentVos(Integer hotelId, int startIndex);

}
