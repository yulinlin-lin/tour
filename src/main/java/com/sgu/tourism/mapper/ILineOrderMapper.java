package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.LineOrder;
import com.sgu.tourism.vo.ChartsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/21 14:38
 */
@Mapper
public interface ILineOrderMapper extends BaseDao<LineOrder> {


    List<LineOrder> queryAllLineOrdes(@Param("page") int page,
                                      @Param("limit") Integer limit,
                                      @Param("key")  String key);

    int getCount(String key);

    int updateDelByUserId(Integer id);

    List<ChartsVo> getLineInCome();

}
