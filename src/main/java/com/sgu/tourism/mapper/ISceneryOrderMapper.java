package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.SceneryOrder;
import com.sgu.tourism.vo.ChartsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 12:28
 */
@Mapper
public interface ISceneryOrderMapper extends BaseDao<SceneryOrder> {

    List<SceneryOrder> queryAllSceneryOrdes(@Param("page") int page,
                                            @Param("limit") Integer limit,
                                            @Param("key") String key);

    int getCount(String key);

    int updateDelBySceneryOrderId(Integer id);

    List<ChartsVo> getSceneryInCome();

}
