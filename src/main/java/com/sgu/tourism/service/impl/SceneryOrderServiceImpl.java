package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.mapper.ISceneryOrderMapper;
import com.sgu.tourism.service.ISceneryOrderService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.ChartsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 11:28
 */
@Service
public class SceneryOrderServiceImpl implements ISceneryOrderService {


    @Autowired
    private ISceneryOrderMapper iSceneryOrderMapper;


    @Override
    public boolean addSceneryOrder(SceneryOrder sceneryOrder) {
        boolean flag = false;
        sceneryOrder.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));

        Integer count = iSceneryOrderMapper.insertObject(sceneryOrder);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SceneryOrder> queryAllSceneryOrdes(Integer page, Integer limit, String key) {
        return iSceneryOrderMapper.queryAllSceneryOrdes((page-1)*limit,limit,key);
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = iSceneryOrderMapper.getCount(key);
        return count;
    }

    @Override
    public int updateSceneryOrderById(SceneryOrder sceneryOrder) {
        int count = 0;
        count = iSceneryOrderMapper.updateObject(sceneryOrder);
        return count;
    }

    @Override
    public int updateDelBySceneryOrderId(Integer id) {

        return iSceneryOrderMapper.updateDelBySceneryOrderId(id);
    }

    @Override
    public List<ChartsVo> getSceneryInCome() {
        return iSceneryOrderMapper.getSceneryInCome();
    }
}
