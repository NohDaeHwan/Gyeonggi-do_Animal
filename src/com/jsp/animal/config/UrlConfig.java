package com.jsp.animal.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UrlConfig implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		
		System.out.println("UrlConfig 접근");
		if (request.getRequestURI().equals("/animal/user/myPage.jsp")) {
			if (session.getAttribute("User") != null) {
				chain.doFilter(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인을 해주세요');");
				out.print("window.location.href = '/animal/user?cmd=loginForm';");
				out.print("</script>");
				out.flush();
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
}
