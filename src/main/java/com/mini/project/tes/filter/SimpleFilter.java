package com.mini.project.tes.filter;

import javax.servlet.*;
import java.io.IOException;

public class SimpleFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        System.out.println("Remote Host:"+request.getRemoteHost());
        System.out.println("Remote Address:"+request.getRemoteAddr());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}

}
