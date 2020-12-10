package com.sgu.tourism;

import com.sgu.tourism.service.ILineOrderService;
import com.sgu.tourism.service.IPrivateCustomService;
import com.sgu.tourism.service.ISceneryService;
import com.sgu.tourism.service.IUserService;
import com.sgu.tourism.vo.ChartsVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISceneryService sceneryService;


    @Autowired
    private IPrivateCustomService iPrivateCustomService;


    @Autowired
    private ILineOrderService iLineOrderService;

    @Test
    public void test(){
        List<ChartsVo> lineInCome = iLineOrderService.getLineInCome();
        for (ChartsVo chartsVo : lineInCome) {
            System.out.println(chartsVo);
        }
    }


}
