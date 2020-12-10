package com.sgu.tourism.service;

import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.SceneryCommentsVo;

import java.util.List;

public interface ISceneryService {
    List<Scenery> findAllScenerys(String page, String limit, String key);

    int getCount(String key);

    boolean insertScenery(Scenery scenery);

    int updateSceneryById(Scenery scenery);

    int deleteSceneryById(Integer sid);


    Scenery selectSceneryById(Integer sid);

    List<Scenery> findAllScenerys();

    List<Scenery> getSceneryByType(Integer sceneryType);


    List<ChartsVo> getSceneryNames();

    Scenery findSceneryById(Integer sceneryId);

    List<SceneryCommentsVo> findSceneryCommentVos(Integer sceneryId, int startIndex);

}
