package com.wjb.newmall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//增加注解说明是配置类启动才会扫描
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(new UserLoginInterceptor())
                //需要拦截的url
        .addPathPatterns("/**")
                //排除拦截的url
        .excludePathPatterns("/user/login","/user/register","/categories");
    }
}
