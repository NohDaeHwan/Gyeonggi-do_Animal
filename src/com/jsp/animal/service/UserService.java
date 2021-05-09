package com.jsp.animal.service;

import com.jsp.animal.domain.user.UserDao;
import com.jsp.animal.domain.user.dto.JoinReqDto;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	// 회원가입 서비스
	public int userJoin(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}
	
	// 유저네임 중복 체크 서비스
	public int usernameCheck(String username) {
		int result = userDao.usernameCheck(username);
		return result;
	}
	
}
