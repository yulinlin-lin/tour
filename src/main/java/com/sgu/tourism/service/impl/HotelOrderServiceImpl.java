package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.mapper.IHotelOrderMapper;
import com.sgu.tourism.service.IHotelOrderService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.ChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 19:34
 */
@Service
public class HotelOrderServiceImpl implements IHotelOrderService {

    @Autowired
    private IHotelOrderMapper iHotelOrderMapper;

    @Override
    public boolean addHotelOrder(HotelOrder hotelOrder) {
        boolean flag = false;
        hotelOrder.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));

        Integer count = iHotelOrderMapper.insertObject(hotelOrder);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<HotelOrder> queryAllHotelOrdes(Integer page, Integer limit, String key) {
        List<HotelOrder> hotelOrders =iHotelOrderMapper.queryAllHotelOrdes((page-1)*limit,limit,key);
        return hotelOrders;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = iHotelOrderMapper.getCount(key);
        return count;
    }

    @Override
    public int updateHotelOrderById(HotelOrder hotelOrder) {
        int count = 0;
        count = iHotelOrderMapper.updateObject(hotelOrder);
        return count;
    }

    @Override
    public int updateDelByHotelOrderId(Integer id) {

        return iHotelOrderMapper.updateDelByHotelOrderId(id);
    }

    @Override
    public List<ChartsVo> getHotelInCome() {

        return iHotelOrderMapper.getHotelInCome();
    }
}
