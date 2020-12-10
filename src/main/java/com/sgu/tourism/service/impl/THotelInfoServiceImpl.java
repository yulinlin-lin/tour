package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.mapper.ITHotelInfoMapper;
import com.sgu.tourism.service.ITHotelInfoService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.HotelCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class THotelInfoServiceImpl implements ITHotelInfoService {
    @Autowired
    private ITHotelInfoMapper mapper;
    @Override
    public List<THotelInfo> findAllHotels(String pageStr, String limitStr, String key) {
        //        对数据进行进行转换
        Integer page = null;
        Integer limit = null;
        if (pageStr != null && !"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if (limitStr!=null && !"".equals(limitStr)){
            limit = Integer.parseInt(limitStr);
        }
        List<THotelInfo> hotelInfos = mapper.findAllHotels((page-1)*limit,limit,key);
        return hotelInfos;
    }

    @Override
    public List<THotelInfo> findAllHotels() {
        return mapper.findAllTHotels();
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = mapper.getCount(key);
        return count;
    }

    @Override
    public boolean insertHotel(THotelInfo hotel) {

        boolean flag = false;

        if (hotel.getImgFileName() == null || "".equals(hotel.getImgFileName())){
            hotel.setImgFileName("pika.jpg");
        }

        if (hotel.getImgFileName() != null && !"".equals(hotel.getImgFileName())){
            String imgFileNameOld = hotel.getImgFileName();
            int i = imgFileNameOld.lastIndexOf("\\");
            String imgFileNameNew = imgFileNameOld.substring(i+1);
            System.out.println("截取后的字符 ：   "+imgFileNameNew);
            hotel.setImgFileName(imgFileNameNew);
        }
        hotel.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));
        Integer count = mapper.insertObject(hotel);
        if (count > 0){
            flag = true;
        }
        return flag;

    }

    @Override
    public int deleteSceneryById(Integer sid) {
        int count = mapper.deleteSceneryById(sid);
        return count;
    }

    @Override
    public int updateHotelById(THotelInfo hotel) {
        int count = 0;
        count = mapper.updateObject(hotel);
        return count;
    }

    @Override
    public THotelInfo queryHotelById(Integer sid) {
        return mapper.queryHotelById(sid);
    }

    @Override
    public boolean updateHotelStatus(Integer id, Integer status) {
        boolean flag = false;
        int count = mapper.updateActiveStatus(id,status);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<THotelInfo> getSceneryByType(Integer hotelType) {
        return mapper.getSceneryByType(hotelType);
    }

    @Override
    public List<ChartsVo> getHotelNames() {
        return mapper.getHotelNames();
    }

    @Override
    public List<HotelCommentsVo> findHotelCommentVos(Integer hotelId, int startIndex) {
        return mapper.findHotelCommentVos(hotelId,startIndex);
    }
}
