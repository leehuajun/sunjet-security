package com.sunjet.sunjetsecuritydemo.web.config;

import com.sunjet.sunjetsecuritydemo.web.filter.ThirdFilter;
import com.sunjet.sunjetsecuritydemo.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        registry.addInterceptor(timeInterceptor);
    }
    /**
     * 使用第三方的 Filter
     * @return
     */
    @Bean
    public FilterRegistrationBean thirdFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        ThirdFilter thirdFilter = new ThirdFilter();
        registrationBean.setFilter(thirdFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");

        registrationBean.setUrlPatterns(urls);

        return registrationBean;
    }


}
