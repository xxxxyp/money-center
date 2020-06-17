package com.fourboy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TimeFilter implements Filter {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.request = (HttpServletRequest) request;
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.out.println(((HttpServletRequest) request).getRequestURI()+" 耗时：" + (System.currentTimeMillis() - start)+"ms");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化时间过滤器！");
    }

    @Override
    public void destroy() {
        System.out.println("销毁时间过滤器！");
    }
}
