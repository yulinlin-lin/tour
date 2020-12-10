package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.Scenery;
import com.sgu.tourism.vo.ChartsVo;
import com.sgu.tourism.vo.SceneryCommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISceneryMapper extends BaseDao<Scenery> {

    List<Scenery> findAllScenerys(@Param("page")Integer page,
                                  @Param("limit")Integer limit,
                                  @Param("key")String key);

    int getCount(@Param("title") String key);

    int deleteSceneryById(Integer sid);


    Scenery selectSceneryById(Integer sid);

    List<Scenery> findAllTScenerys();

    List<Scenery> getSceneryByType(Integer sceneryType);

    List<ChartsVo> getSceneryNames();

    Scenery findSceneryById(Integer sceneryId);

    List<SceneryCommentsVo> findSceneryCommentVos(@Param("sceneryId") Integer sceneryId,
                                                  @Param("startIndex") int startIndex);

}
