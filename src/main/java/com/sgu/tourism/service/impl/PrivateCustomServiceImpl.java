package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.Privatecustom;
import com.sgu.tourism.mapper.IPrivateCustomMapper;
import com.sgu.tourism.service.IPrivateCustomService;
import com.sgu.tourism.util.DataToos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author huang
 * @date 2020/11/29 13:25
 */
@Service
public class PrivateCustomServiceImpl implements IPrivateCustomService {

    @Autowired
    private IPrivateCustomMapper iPrivateCustomMapper;

    @Override
    public int addPrivateCustom(Privatecustom privatecustom) {
        privatecustom.setCreateTime(DataToos.getNowDateYYYY_MM_DD(new Date()));
        return iPrivateCustomMapper.insertObject(privatecustom);
    }

    @Override
    public List<Privatecustom> queryAllPirvateCustoms(Integer page, Integer limit, String key) {
        List<Privatecustom> privatecustoms = iPrivateCustomMapper.queryAllPirvateCustoms((page-1)*limit,limit,key);
        return privatecustoms;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = iPrivateCustomMapper.getCount(key);
        return count;
    }

    @Override
    public int updatePrivateCourtomById(Privatecustom privatecustom) {
        return iPrivateCustomMapper.updateObject(privatecustom);
    }

    @Override
    public int deleteByPrivateId(Integer id) {
        return iPrivateCustomMapper.deleteByPrivateId(id);
    }
}
