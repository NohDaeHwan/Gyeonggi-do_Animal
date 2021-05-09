package com.jsp.animal.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jsp.animal.config.DB;
import com.jsp.animal.domain.user.dto.JoinReqDto;

public class UserDao {
	
	// 회원가입
	public int save(JoinReqDto dto) {
		String sql = "INSERT INTO user(username, password, email, roadAddress, jibunAddress, createDate) VALUES(?, ?, ?, ?, ?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getRoadAddress());
			pstmt.setString(5, dto.getJibunAddress());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	// 유저네임 중복 체크
	public int usernameCheck(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	 
}
