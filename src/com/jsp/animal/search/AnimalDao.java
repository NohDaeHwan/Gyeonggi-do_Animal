package com.jsp.animal.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jsp.animal.config.DB;
import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;

public class AnimalDao {
	
	// 동물병원, 동물약국 검색
	public ArrayList<HPReqDto> searchHP(String cmd, int page, String query1, String query2) { 
		Connection conn = DB.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
	    String sql = "";
        try {
        	if ((query1 == null && query2 == null) || query2 == "") {
        		sql = "SELECT * FROM " + cmd + " ORDER BY SIGUN_NM LIMIT ?, 30";
        		pstmt = conn.prepareStatement(sql);
            	pstmt.setInt(1, page * 30);
        	} else {
        		sql = "SELECT * FROM " + cmd + " WHERE " + query1 + " LIKE ? ORDER BY SIGUN_NM LIMIT ?, 30";
        		pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, "%"+query2+"%");
            	pstmt.setInt(2, page * 30);
        	}
        	
     		rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HPReqDto animal = new HPReqDto();
        		animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        		animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        		animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
        		animal.setLOCPLC_FACLT_TELNO(rs.getString("LOCPLC_FACLT_TELNO"));
        		animal.setROADNM_ZIP_CD(rs.getString("ROADNM_ZIP_CD"));
        		animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
        			 
        		animals.add(animal);
        	}	 
     		return animals;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	DB.close(conn, pstmt, rs);
        }     
		return null;
	}
	
	// 동물보호시설 검색
	public ArrayList<FReqDto> searchF(String cmd, int page) { 
		String sql = "SELECT * FROM " + cmd + " ORDER BY SIGUN_NM LIMIT ?, 30";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<FReqDto> animals = new ArrayList<FReqDto>();
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, page * 30);
     		rs = pstmt.executeQuery();
     		while (rs.next()) {
     			FReqDto animal = new FReqDto();
        		animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        		animal.setENTRPS_NM(rs.getString("ENTRPS_NM"));
        		animal.setACEPTNC_ABLTY_CNT(rs.getString("ACEPTNC_ABLTY_CNT"));
        		animal.setREFINE_ZIP_CD(rs.getString("REFINE_ZIP_CD"));
        		animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
        			 
        		animals.add(animal);
        	}	 
     		return animals;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	DB.close(conn, pstmt, rs);
        }     
		return null;
	}
	
	// 동물병원, 동물약국, 동물보호시설 개수
	public int count(String cmd, String query1, String query2) { 
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if ((query1 == null && query2 == null) || query2 == "") {
        		sql = "SELECT count(*) FROM " + cmd;
        		pstmt = conn.prepareStatement(sql);
        	} else {
        		sql = "SELECT count(*) FROM " + cmd + " WHERE " + query1 + " LIKE ?";
        		pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, "%"+query2+"%");
        	}
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
	
	// User의 주소 근처 동물병원, 동물약국 검색
	public ArrayList<HPReqDto> addressSearch(String cmd, String address) { 
		String sql = "SELECT * FROM " + cmd + " WHERE REFINE_LOTNO_ADDR LIKE ? ORDER BY BIZPLC_NM";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, "%"+address+"%");
     		rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HPReqDto animal = new HPReqDto();
        		animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        		animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
        		animal.setLOCPLC_FACLT_TELNO(rs.getString("LOCPLC_FACLT_TELNO"));
        		animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
        		animal.setREFINE_LOTNO_ADDR(rs.getString("REFINE_LOTNO_ADDR"));
        		animal.setTOTAL_RANK(rs.getFloat("TOTAL_RANK"));
        		animals.add(animal);
        	}     		 
     		return animals;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	DB.close(conn, pstmt, rs);
        }     
		return null;
	}
	
	// 메인페이지에 동물병원, 둥물약국 3개씩 보여주기
	public ArrayList<HPReqDto> indexSearch(String cmd, String address) { 
		String sql = "SELECT * FROM " + cmd + " WHERE REFINE_LOTNO_ADDR LIKE ? ORDER BY BIZPLC_NM LIMIT 3";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
        try {
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, "%"+address+"%");
     		rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HPReqDto animal = new HPReqDto();
        		animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        		animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        		animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
        		animal.setROADNM_ZIP_CD(rs.getString("ROADNM_ZIP_CD"));
        		animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR").substring(4));
        		animal.setREFINE_LOTNO_ADDR(rs.getString("REFINE_LOTNO_ADDR"));
        		animal.setTOTAL_RANK(rs.getFloat("TOTAL_RANK"));
        		animals.add(animal);
        	}  
     		return animals;
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	DB.close(conn, pstmt, rs);
        }     
		return null;
	}
	
	// 진료일지 등록시 병원 평점 변경
	public int hosptlRankUpdate(float rank, String address, String BIZPLC_NM) {
		String sql = "UPDATE animalhosptl set TOTAL_RANK = ? WHERE BIZPLC_NM = ? AND SIGUN_NM = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, rank);
			pstmt.setString(2, BIZPLC_NM);
			pstmt.setString(3, address);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt);
		}		
		return -1;
	}	
	
}
