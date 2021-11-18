package com.mini.project.tes.controller.rest.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Winner [Alpabit]
 *
 * Oct 8, 2019
 */
@SuppressWarnings("deprecation")
public class ServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ServiceInterceptor productServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor);
    }
}