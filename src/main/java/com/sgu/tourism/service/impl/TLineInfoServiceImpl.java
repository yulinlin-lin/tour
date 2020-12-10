package com.sgu.tourism.service.impl;

import com.sgu.tourism.entity.TLineInfo;
import com.sgu.tourism.mapper.ITLineInfoMapper;
import com.sgu.tourism.service.ITLineInfoService;
import com.sgu.tourism.util.DataToos;
import com.sgu.tourism.vo.UsersCommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TLineInfoServiceImpl implements ITLineInfoService {

    @Autowired
    private ITLineInfoMapper mapper;
    @Override
    public List<TLineInfo> findAllLines(String pageStr, String limitStr, String key) {
        //        对数据进行进行转换
        Integer page = null;
        Integer limit = null;
        if (pageStr != null && !"".equals(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if (limitStr!=null && !"".equals(limitStr)){
            limit = Integer.parseInt(limitStr);
        }
        List<TLineInfo> lines = mapper.findAllLines((page-1)*limit,limit,key);
        return lines;
    }

    @Override
    public int getCount(String key) {
        int count = 0;
        count = mapper.getCount(key);
        return count;
    }

    @Override
    public boolean insertObject(TLineInfo lineInfo) {
        boolean flag = false;

        if (lineInfo.getImgFileName() == null || lineInfo.getImgFileName() == ""){
            lineInfo.setImgFileName("pika.jpg");
        }

        if (lineInfo.getImgFileName() != null && !"".equals(lineInfo.getImgFileName())){
            String imgFileNameOld = lineInfo.getImgFileName();
            int i = imgFileNameOld.lastIndexOf("\\");
            String imgFileNameNew = imgFileNameOld.substring(i+1);
            System.out.println("截取后的字符 ：   "+imgFileNameNew);
            lineInfo.setImgFileName(imgFileNameNew);
        }

        lineInfo.setCreateTime(DataToos.getNowDateYYYY_MM_DD_2(new Date()));

        Integer integer = mapper.insertObject(lineInfo);
        if (integer > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateActiveStatus(Integer id, Integer status) {
        boolean flag = false;
        int count = mapper.updateActiveStatus(id,status);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean updateLine(TLineInfo lineInfo) {
        boolean flag = false;
        int count = mapper.updateObject(lineInfo);
        if (count > 0){
            flag = true;
        }
        return flag;
    }

    @Override
    public int deleteLineById(int parseInt) {
        int count = 0;
        count = mapper.deleteLineById(parseInt);
        return count;
    }


    @Override
    public int deleteAllLine(List<Integer> lineIds) {
        int count = mapper.deleteAllLine(lineIds);
        return count;
    }

    @Override
    public List<TLineInfo> findAllLines() {
        return mapper.findAllTLins();
    }

    @Override
    public List<TLineInfo> findLinesByLineType(Integer lineType) {

        return mapper.findLinesByLineType(lineType);
    }

    @Override
    public TLineInfo findOneLineById(Integer lineId) {
        return mapper.findOneLineById(lineId);
    }

    @Override
    public List<UsersCommentsVo> findUsersCommentsVos(Integer sceneryId,Integer startIndex) {
        return mapper.findUsersCommentsVos(sceneryId,startIndex);
    }

}
