package com.jsp.animal.service;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.domain.user.UserDao;
import com.jsp.animal.domain.user.dto.JoinReqDto;
import com.jsp.animal.domain.user.dto.LoginReqDto;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	// 회원가입
	public int userJoin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		
		JoinReqDto dto = new JoinReqDto(username, password, email, roadAddress, jibunAddress);
		
		int result = userDao.save(dto);
		return result;
	}
	
	// 로그인
	public User userLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("id");
		String password = request.getParameter("password");
		
		LoginReqDto dto = new LoginReqDto(username, password);
		
		return userDao.findByUsername(dto);
	}
	
	// 재로그인
	public User userReLogin(LoginReqDto dto) {			
		return userDao.findByUsername(dto);
	}
	
	// 유저네임 중복 체크
	public int usernameCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader br =  request.getReader();
		String username = br.readLine();
		
		int result = userDao.usernameCheck(username);
		return result;
	}
	
	// 패스워드 일치 확인
	public int passwordCheck(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int result = userDao.passwordCheck(username, password);
		return result;
	}
	
	// 회원정보 수정
	public int userUpdate(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		
		int result = userDao.userUpdate(username, email, roadAddress, jibunAddress);
		return result;
	}
	
	// 회원 비밀번호 수정
	public int userPasswordUpdate(String username, String password) {
		int result = userDao.userPasswordUpdate(username, password);
		return result;
	}
	
	// 회원탈퇴
	public int userWithdrawal(String username, String password) {
		return userDao.userWithdrawal(username, password);
	}
	
	// 회원탈퇴 시 동물일지 삭제
	public int userJournalDelete(int userId) {
		return userDao.userJournalDelete(userId);
	}
}
