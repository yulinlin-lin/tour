package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.mapper.ISceneryMapper;
import com.sgu.tourism.service.ISceneryService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.SceneryCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SceneryServiceImpl implements ISceneryService {

    @Autowired
    private ISceneryMapper sceneryMapper;

    @Override
    public List<Scenery> findAllScenerys(String pageStr, String limitStr, String key) {

        //        对数据进行进行转换
        Integer page = null;
        Integer limit = null;
        if (pageStr != null && !"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if (limitStr!=null && !"".equals(limitStr)){
            limit = Integer.parseInt(limitStr);
        }
        List<Scenery> scenerys = sceneryMapper.findAllScenerys((page-1)*limit,limit,key);
        return scenerys;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = sceneryMapper.getCount(key);
        return count;
    }

    @Override
    public boolean insertScenery(Scenery scenery) {
        boolean flag = false;
        if (scenery.getImgFileName() == null || scenery.getImgFileName() == ""){
            System.out.println("它是空的===========================");
            scenery.setImgFileName("pika.jpg");
        }

        if (scenery.getImgFileName() != null && !"".equals(scenery.getImgFileName())){
            String imgFileNameOld = scenery.getImgFileName();
            int i = imgFileNameOld.lastIndexOf("\\");
            String imgFileNameNew = imgFileNameOld.substring(i+1);
            System.out.println("截取后的字符 ：   "+imgFileNameNew);
            scenery.setImgFileName(imgFileNameNew);
        }
        scenery.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));
        Integer integer = sceneryMapper.insertObject(scenery);
        if (integer > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public int updateSceneryById(Scenery scenery) {
        int count = 0;
        count = sceneryMapper.updateObject(scenery);
        return count;
    }

    @Override
    public int deleteSceneryById(Integer sid) {
        int count = sceneryMapper.deleteSceneryById(sid);
        return count;
    }

    @Override
    public Scenery selectSceneryById(Integer sid) {
        return sceneryMapper.selectSceneryById(sid);
    }

    @Override
    public List<Scenery> findAllScenerys() {
        return sceneryMapper.findAllTScenerys();
    }

    @Override
    public List<Scenery> getSceneryByType(Integer sceneryType) {
        return sceneryMapper.getSceneryByType(sceneryType);
    }

    @Override
    public List<ChartsVo> getSceneryNames() {
        return sceneryMapper.getSceneryNames();
    }

    @Override
    public Scenery findSceneryById(Integer sceneryId) {
        return sceneryMapper.findSceneryById(sceneryId);
    }

    @Override
    public List<SceneryCommentsVo> findSceneryCommentVos(Integer sceneryId, int startIndex) {
        return sceneryMapper.findSceneryCommentVos(sceneryId,startIndex);
    }

}
