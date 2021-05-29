package com.jsp.animal.service;

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
	public int userJoin(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}
	
	// 로그인
	public User userLogin(LoginReqDto dto) {
		return userDao.findByUsername(dto);
	}
	
	// 유저네임 중복 체크
	public int usernameCheck(String username) {
		int result = userDao.usernameCheck(username);
		return result;
	}
	
	// 패스워드 일치 확인
	public int passwordCheck(String username, String password) {
		int result = userDao.passwordCheck(username, password);
		return result;
	}
	
	// 회원정보 수정
	public int userUpdate(String username, String email, String roadAddress, String jibunAddress) {
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
		
}
