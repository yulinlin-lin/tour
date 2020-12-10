package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.vo.UsersCommentsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ITLineInfoMapper extends BaseDao<TLineInfo> {

    List<TLineInfo> findAllLines(@Param("page") Integer page,
                                 @Param("limit") Integer limit,
                                 @Param("key") String key);

    int getCount(@Param("key")String key);

    int updateActiveStatus(@Param("lineId")Integer id, @Param("status")Integer status);

    int deleteLineById(@Param("lineId")int lineId);

    int deleteAllLine(List<Integer> list);

    List<TLineInfo> findAllTLins();

    List<TLineInfo> findLinesByLineType(Integer lineType);

    TLineInfo findOneLineById(Integer lineId);


    List<UsersCommentsVo> findUsersCommentsVos(@Param("lineId") Integer lineId,
                                               @Param("startIndex") Integer startIndex);

}
