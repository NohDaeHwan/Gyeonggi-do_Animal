package com.jsp.animal.domain.journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jsp.animal.config.DB;
import com.jsp.animal.domain.journal.dto.JournalSaveReqDto;

public class JournalDao {
	
	// 동물 진료 일지 저장
	public int save(JournalSaveReqDto dto) {
		String sql = "INSERT INTO journal(userId, title, treatDate, visitHosptl, hosptlRank, content, createDate) VALUES(?, ?, ?, ?, ?, ?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setDate(3, dto.getTreatDate());
			pstmt.setString(4, dto.getVisitHosptl());
			pstmt.setInt(5, dto.getRank());
			pstmt.setString(6, dto.getContent());
			int result = pstmt.executeUpdate();		
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	// 동물 진료 일지 검색
	public List<Journal> findAll(int page, int userId) {
		String sql = "SELECT * FROM journal WHERE userId = ? ORDER BY id DESC LIMIT ?, 5";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Journal> journals = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, page * 5);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Journal journal = new Journal();
				journal.setId(rs.getInt("id"));
				journal.setUserId(rs.getInt("userId"));
				journal.setTitle(rs.getString("title"));
				journal.setTreatDate(rs.getDate("treatDate"));
				journal.setContent(rs.getString("content"));
				journal.setCreateDate(rs.getTimestamp("createDate"));
				journals.add(journal);
			}
			return journals;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}	
		return null;
	}
	
	// 동물 진료 일지 개수 가져오기
	public int count(int userId) {
		String sql = "SELECT count(*) FROM journal WHERE userId = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public Journal findById(int id) {
		String sql = "SELECT * FROM journal WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Journal journal = new Journal();
				journal.setId(rs.getInt("id"));
				journal.setUserId(rs.getInt("userId"));
				journal.setTitle(rs.getString("title"));
				journal.setTreatDate(rs.getDate("treatDate"));
				journal.setContent(rs.getString("content"));
				journal.setCreateDate(rs.getTimestamp("createDate"));
				return journal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}	
		return null;
	}
	
	public float rankAvg(String visit) {
		String sql = "SELECT AVG(hosptlRank) FROM journal WHERE visitHosptl = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, visit);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getFloat(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
}
