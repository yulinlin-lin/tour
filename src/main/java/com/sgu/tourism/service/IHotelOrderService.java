package com.sgu.tourism.service;

import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.vo.ChartsVo;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 19:33
 */
public interface IHotelOrderService {
    boolean addHotelOrder(HotelOrder hotelOrder);

    List<HotelOrder> queryAllHotelOrdes(Integer page, Integer limit, String key);

    int getCount(String key);

    int updateHotelOrderById(HotelOrder hotelOrder);

    int updateDelByHotelOrderId(Integer id);

    List<ChartsVo> getHotelInCome();

}
