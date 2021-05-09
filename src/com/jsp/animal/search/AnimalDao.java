package com.jsp.animal.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jsp.animal.config.DB;
import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;

public class AnimalDao {
	
	public ArrayList<HPReqDto> searchHP(String cmd, int page) { // 동물병원, 동물약국 검색
		String sql = "SELECT * FROM " + cmd + " ORDER BY SIGUN_NM LIMIT ?, 30";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
        try {
        	 pstmt = conn.prepareStatement(sql);
        	 pstmt.setInt(1, page * 30);
     		 rs = pstmt.executeQuery();
     		while (rs.next()) {
     			HPReqDto animal = new HPReqDto();
        		animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        		animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        		animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
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
	
	public ArrayList<FReqDto> searchF(String cmd, int page) { // 동물보호시설 검색
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
	
	public int count(String cmd) { // 동물병원, 동물약국, 동물보호시설 개수
		String sql = "SELECT count(*) FROM " + cmd;
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public ArrayList<Animal> addressSearch(String cmd, String address) { // User의 주소 근처 동물병원, 동물약국 검색
		String sql = "SELECT * FROM " + cmd + " WHERE REFINE_LOTNO_ADDR LIKE ?";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
        	 pstmt = conn.prepareStatement(sql);
        	 pstmt.setString(1, "%"+address+"%");
     		 rs = pstmt.executeQuery();
     		while (rs.next()) {
        			 Animal animal = new Animal();
        			 animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        			 animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        			 animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
        			 animal.setROADNM_ZIP_CD(rs.getString("ROADNM_ZIP_CD"));
        			 animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
        			 animal.setREFINE_LOTNO_ADDR(rs.getString("REFINE_LOTNO_ADDR"));
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
	
	public ArrayList<Animal> indexSearch(String address) { 
		String sql1 = "SELECT * FROM animalhosptl WHERE REFINE_LOTNO_ADDR LIKE ? LIMIT 3";
		String sql2 = "SELECT * FROM animalpharmacy WHERE REFINE_LOTNO_ADDR LIKE ? LIMIT 3";
        Connection conn = DB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
        	 pstmt = conn.prepareStatement(sql1);
        	 pstmt.setString(1, "%"+address+"%");
     		 rs = pstmt.executeQuery();
     		while (rs.next()) {
        			 Animal animal = new Animal();
        			 animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
        			 animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
        			 animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
        			 animal.setROADNM_ZIP_CD(rs.getString("ROADNM_ZIP_CD"));
        			 animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
        			 animal.setREFINE_LOTNO_ADDR(rs.getString("REFINE_LOTNO_ADDR"));
        			 animals.add(animal);
        	}  
     		rs.close();
     		pstmt.close();
     		pstmt = conn.prepareStatement(sql2);
     		pstmt.setString(1, "%"+address+"%");
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
       			 Animal animal = new Animal();
       			 animal.setSIGUN_NM(rs.getString("SIGUN_NM"));
       			 animal.setBIZPLC_NM(rs.getString("BIZPLC_NM"));
       			 animal.setBSN_STATE_NM(rs.getString("BSN_STATE_NM"));
       			 animal.setROADNM_ZIP_CD(rs.getString("ROADNM_ZIP_CD"));
       			 animal.setREFINE_ROADNM_ADDR(rs.getString("REFINE_ROADNM_ADDR"));
       			 animal.setREFINE_LOTNO_ADDR(rs.getString("REFINE_LOTNO_ADDR"));
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
	
}
