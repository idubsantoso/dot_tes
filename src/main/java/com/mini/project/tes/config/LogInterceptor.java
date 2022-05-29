package com.mini.project.tes.config;

import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.UUID;

public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        MDC.clear();
        UUID requestId = UUID.randomUUID();
        MDC.put("requestTime", String.valueOf(LocalTime.now()));
        MDC.put("requestId", String.valueOf(requestId));
        return true;
    }
}
