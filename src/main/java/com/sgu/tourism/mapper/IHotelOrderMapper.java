package com.sgu.tourism.mapper;

import com.sgu.tourism.common.dao.BaseDao;
import com.sgu.tourism.entity.HotelOrder;
import com.sgu.tourism.vo.ChartsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huang
 * @date 2020/11/23 19:34
 */
@Mapper
public interface IHotelOrderMapper extends BaseDao<HotelOrder> {

    List<HotelOrder> queryAllHotelOrdes(@Param("page") int page,
                                        @Param("limit") Integer limit,
                                        @Param("key")  String key);

    int getCount(String key);

    int updateDelByHotelOrderId(Integer id);

    List<ChartsVo> getHotelInCome();

}
