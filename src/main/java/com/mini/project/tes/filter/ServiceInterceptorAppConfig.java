package com.mini.project.tes.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
public class ServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ServiceInterceptor productServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor);
    }
}