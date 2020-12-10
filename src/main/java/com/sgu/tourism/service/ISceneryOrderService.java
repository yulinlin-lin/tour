package com.sgu.tourism.service;

import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.vo.ChartsVo;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 11:27
 */
public interface ISceneryOrderService {
    boolean addSceneryOrder(SceneryOrder sceneryOrder);

    List<SceneryOrder> queryAllSceneryOrdes(Integer page, Integer limit, String key);

    int getCount(String key);

    int updateSceneryOrderById(SceneryOrder sceneryOrder);

    int updateDelBySceneryOrderId(Integer id);

    List<ChartsVo> getSceneryInCome();

}
