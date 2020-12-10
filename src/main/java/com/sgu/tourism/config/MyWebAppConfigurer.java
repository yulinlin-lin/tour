package com.sgu.tourism.config;

import com.sgu.tourism.intercetor.BasicInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huang
 * @date 2020/11/17 20:58
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:D:\\eclipseWork\\ideaWork\\upload\\");
    }


    /**
     * 配置拦截器，阻止普通用户和游客进入管理员界面，阻止游客进行个人信息有关操作
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        只有在下面填写了相应url才会进行拦截；    只有匹配下面的url的才会去做处理；
        registry.addInterceptor(new BasicInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/loginHandle","/index");
        // 这个方法是直接放过那些url;
    }



    /*配置项目打开路径*/
/*    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login");
                    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }*/
}
