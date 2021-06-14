package com.jsp.animal.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.domain.user.dto.LoginReqDto;
import com.jsp.animal.service.AnimalService;
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		if (cmd.equals("loginForm")) { // 로그인 페이지로 이동
			RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("joinForm")) { // 회원가입 페이지로 이동
			RequestDispatcher dis = request.getRequestDispatcher("user/joinForm.jsp");
			dis.forward(request, response);
		} else if (cmd.equals("logout")) { // 로그아웃
			session.invalidate();
			response.sendRedirect("index.jsp");
		} else if (cmd.equals("passwordCheckForm1")) { // 회원정보 수정 페이지로 이동 전 비밀번호 체크 페이지로 이동			
			if (user != null) {
				request.setAttribute("passwordCheck", true);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);	
			} else {
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("passwordCheckForm2")) { // 회원정보 탈퇴 페이지로 이동 전 비밀번호 체크 페이지로 이동
			if (user != null) {
				request.setAttribute("withdrawalPrePasswordCheck", true);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);
			} else {
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("passwordUpdateForm")) { // 비밀번호 변경 페이지로 이동
			if (user != null) {
				request.setAttribute("passwordUpdate", true);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);
			} else {
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("animalhosptl") || cmd.equals("animalpharmacy")) { // User 주변 동물병원, 동물약국 보여주기
				AnimalService service = new AnimalService();
				service.addressSearch(request, response);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		if (cmd.equals("join")) { // 회원가입 하기	
			int result = userService.userJoin(request, response);
			if (result == 1) { // 회원가입 성공
				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			} else { // 회원가입 실패
				Script.back(response, "회원가입 실패");
			}
		} else if (cmd.equals("login")) { // 로그인 하기	
			User user = userService.userLogin(request, response);
			if (user != null) { // 로그인 성공하면 세션 주기
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				
				AnimalService service = new AnimalService();
				service.indexSearch(request, response);
				
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
				dis.forward(request, response);
			} else { // 로그인 실패
				Script.back(response, "일치하는 회원이 없습니다.");
			}			
		} else if (cmd.equals("usernameCheck")) { // 유저네임 중복 체크
			int result = userService.usernameCheck(request, response);
			PrintWriter out = response.getWriter();
			if (result == 1) { 
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();
		} else if (cmd.equals("passwordCheck")) { // 회원정보 수정 페이지로 이동 전 비밀번호 체크
			int result = userService.passwordCheck(request, response);
			PrintWriter out = response.getWriter();
			if (result == 1) { 
				out.print("ok");
			} else {
				out.print("fail");
			}
			out.flush();
		} else if (cmd.equals("userUpdateForm")) { // 회원정보 수정 페이지로 이동
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				request.setAttribute("update", true);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);
			} else { // User 세션이 없으면 로그인 폼으로 이동
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("userUpdate")) { // 회원정보 수정
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				int result = userService.userUpdate(request, response);
				PrintWriter out = response.getWriter();
				if (result == 1) {
					out.print("ok");
					LoginReqDto dto = new LoginReqDto(user.getUsername(), user.getPassword()); // 세션 변경
					User userEntity = userService.userReLogin(dto);
					session.setAttribute("User", userEntity);
				} else {
					out.print("fail");
				}
				out.flush();
			} else { // User 세션이 없으면 로그인 폼으로 이동
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("userPasswordUpdate")) { // 회원 비밀번호 변경
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				int result = userService.userPasswordUpdate(username, password);
				PrintWriter out = response.getWriter();
				if (result == 1) {
					out.print("ok");
					LoginReqDto dto = new LoginReqDto(username, password); // 세션 변경
					User userEntity = userService.userReLogin(dto);
					session.setAttribute("User", userEntity);
				} else {
					out.print("fail");
				}
				out.flush();
			} else { // User 세션이 없으면 로그인 폼으로 이동
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("userWithdrawalForm")) { // 회원탈퇴 페이지 보여주기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("User");
			if (user != null) {
				request.setAttribute("withdrawal", true);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
				dis.forward(request, response);
			} else {
				Script.lognError(response, "로그인을 해주세요");
			}
		} else if (cmd.equals("withdrawal")) { // 회원탈퇴하기
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("User");
			if (user != null) {
				UserService service = new UserService();
				int result =  service.userWithdrawal(user.getUsername(), user.getPassword());
				if (result == 1) {
					service.userJournalDelete(user.getId());
					session.invalidate();
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('회원 탈퇴에 성공했습니다.');");
					out.print("window.location.href = '/animal/index.jsp';");
					out.print("</script>");
					out.flush();
				} else {
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('회원 탈퇴에 실패했습니다.');");
					out.print("window.location.reload();");
					out.print("</script>");
					out.flush();
				}
			} else {
				Script.lognError(response, "로그인을 해주세요");
			}
		}
	}

}
