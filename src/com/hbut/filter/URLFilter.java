package com.hbut.filter;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class URLFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(URLFilter.class);

    public void init(FilterConfig arg0) throws ServletException {
        logger.info("Filter 初始化");
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        logger.info("URL = " + request.getContextPath());
        chain.doFilter(req, res);
    }

    public void destroy() {
        logger.info("Filter 已关闭");
    }
}
