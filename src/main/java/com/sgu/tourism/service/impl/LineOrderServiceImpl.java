package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.entity.THotelInfo;
import com.sgu.tourism.mapper.ILineOrderMapper;
import com.sgu.tourism.service.ILineOrderService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.ChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/21 14:38
 */
@Service
public class LineOrderServiceImpl implements ILineOrderService {

    @Autowired
    private ILineOrderMapper iLineOrderMapper;


    @Override
    public boolean addLineOrder(LineOrder lineOrder) {
        boolean flag = false;
        lineOrder.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));
        Integer count = iLineOrderMapper.insertObject(lineOrder);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<LineOrder> queryAllLineOrdes(Integer page, Integer limit, String key) {
        List<LineOrder> lineOrders = iLineOrderMapper.queryAllLineOrdes((page-1)*limit,limit,key);
        return lineOrders;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = iLineOrderMapper.getCount(key);
        return count;
    }

    @Override
    public int updateLineOrderById(LineOrder lineOrder) {
        int count = 0;
        count = iLineOrderMapper.updateObject(lineOrder);
        return count;
    }

    @Override
    public int updateDelByUserId(Integer id) {

        return iLineOrderMapper.updateDelByUserId(id);
    }

    @Override
    public List<ChartsVo> getLineInCome() {

        return iLineOrderMapper.getLineInCome();
    }


}
