package com.jsp.animal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.dto.JoinReqDto;
import com.jsp.animal.service.UserService;
import com.jsp.animal.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("loginForm")) { // 로그인 페이지로 이동
			RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("joinForm")) { // 회원가입 페이지로 이동
			RequestDispatcher dis = request.getRequestDispatcher("user/joinForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("logout")) { // 로그아웃
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		if (cmd.equals("join")) { // 회원가입 하기
			String username = request.getParameter("id");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String roadAddress = request.getParameter("roadAddress");
			String jibunAddress = request.getParameter("jibunAddress");
			
			JoinReqDto dto = new JoinReqDto(username, password, email, roadAddress, jibunAddress);
			
			int result = userService.userJoin(dto);
			if (result == 1) { // 회원가입 성공
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			} else { // 회원가입 실패
				Script.back(response, "회원가입 실패");
			}
		} else if (cmd.equals("usernameCheck")) { // 유저네임 중복 체크
			BufferedReader br =  request.getReader();
			String username = br.readLine();
			System.out.println(username);
			int result = userService.usernameCheck(username);
			PrintWriter out = response.getWriter();
			if (result == 1) { 
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();
		} 
	}

}
