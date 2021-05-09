package com.jsp.animal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.domain.user.dto.JoinReqDto;
import com.jsp.animal.domain.user.dto.LoginReqDto;
import com.jsp.animal.search.Animal;
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
		} else if (cmd.equals("login")) { // 로그인 하기
			String username = request.getParameter("id");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto(username, password);
			
			User user = userService.userLogin(dto);
			if (user != null) { // 로그인 성공하면 세션 주기
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				
				String address = "";
				if (user.getJibunAddress().contains("수원시") || user.getJibunAddress().contains("성남시") || user.getJibunAddress().contains("안양시") ||
						user.getJibunAddress().contains("안산시") || user.getJibunAddress().contains("용인시") || user.getJibunAddress().contains("고양시")) {
					String str[] = user.getJibunAddress().split(" ");
					address = str[3];
				} else {
					String str[] = user.getJibunAddress().split(" ");
					address = str[2];
				}
				
				ArrayList<Animal> animalDto = new ArrayList<Animal>();
				ArrayList<Animal> hosptls = new ArrayList<Animal>();
				ArrayList<Animal> pharmacys = new ArrayList<Animal>();
				
				AnimalService service = new AnimalService();
				animalDto = service.indexSearch(address);
				System.out.println(animalDto.size());
				for (int i = 0; i < animalDto.size(); i++) {
					Animal animal = new Animal();
					animal.setSIGUN_NM(animalDto.get(i).getSIGUN_NM());
					animal.setBIZPLC_NM(animalDto.get(i).getBIZPLC_NM());
					animal.setBSN_STATE_NM(animalDto.get(i).getBSN_STATE_NM());
					animal.setROADNM_ZIP_CD(animalDto.get(i).getROADNM_ZIP_CD());
					animal.setREFINE_ROADNM_ADDR(animalDto.get(i).getREFINE_ROADNM_ADDR());
					
					if (i < 3) {
						hosptls.add(animal);
					} else {
						pharmacys.add(animal);
					}				
				}
				
				request.setAttribute("hosptls", hosptls);		
				request.setAttribute("pharmacys", pharmacys);
				
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp");
				dis.forward(request, response);
			} else { // 로그인 실패
				Script.back(response, "로그인 실패");
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
