package com.hbut.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class URLFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter 初始化");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("URL = " + request.getContextPath());
		// System.out.println("鎷︽埅 URI="+request.getRequestURI());
		chain.doFilter(req, res);
	}

	public void destroy() {
		System.out.println("Filter 已关闭");
	}
}
