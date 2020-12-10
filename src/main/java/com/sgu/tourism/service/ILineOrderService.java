package com.sgu.tourism.service;

import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.vo.ChartsVo;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/21 14:37
 */
public interface ILineOrderService {
    boolean addLineOrder(LineOrder lineOrder);


    List<LineOrder> queryAllLineOrdes(Integer page, Integer limit, String key);

    int getCount(String key);

    int updateLineOrderById(LineOrder lineOrder);


    int updateDelByUserId(Integer id);

    List<ChartsVo> getLineInCome();

}
