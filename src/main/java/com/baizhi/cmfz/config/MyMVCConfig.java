package com.baizhi.cmfz.config;

import com.baizhi.cmfz.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    //welcome页面和部分页面路径映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("main/main").setViewName("main/main");
        registry.addViewController("album/albumManagement").setViewName("album/albumManagement");
        registry.addViewController("banner/bannerManagement").setViewName("banner/bannerManagement");
//        registry.addViewController("/main.html").setViewName("dashboard");
    }
    //国际化问题
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**","/js/**");
//
//    }
}
