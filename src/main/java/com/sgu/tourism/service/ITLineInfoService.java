package com.sgu.tourism.service;

import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.vo.UsersCommentsVo;

import java.util.List;

public interface ITLineInfoService {
    List<TLineInfo> findAllLines(String page, String limit, String key);

    int getCount(String key);

    boolean insertObject(TLineInfo lineInfo);

    boolean updateActiveStatus(Integer id, Integer status);

    boolean updateLine(TLineInfo lineInfo);

    int deleteLineById(int parseInt);

    int deleteAllLine(List<Integer> lineIds);

    List<TLineInfo> findAllLines();


    List<TLineInfo> findLinesByLineType(Integer lineType);

    TLineInfo findOneLineById(Integer lineId);


    List<UsersCommentsVo> findUsersCommentsVos(Integer lineId,Integer startIndex);

}
