package com.jsp.animal.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	public static void back(HttpServletResponse response, String msg) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void lognError(HttpServletResponse response, String msg) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("window.location.href = '/animal/user?cmd=loginForm';");
			out.print("</script>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
