package com.jsp.animal.service;

import java.util.ArrayList;

import com.jsp.animal.search.AnimalDao;
import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;

public class AnimalService {
	
	// 유기보호시설 검색
	public ArrayList<FReqDto> searchF(String cmd, int page) {
		AnimalDao dao = new AnimalDao();
		return dao.searchF(cmd, page);
	}
	
	// 동물병원, 동물약국 검색
	public ArrayList<HPReqDto> searchHP(String cmd, int page, String query1, String query2) {
		AnimalDao dao = new AnimalDao();
		return dao.searchHP(cmd, page, query1, query2);
	}
	
	// 동물병원, 동물약국, 유기보호시설 개수
	public int count(String cmd, String query1, String query2) {
		AnimalDao dao = new AnimalDao();
		return dao.count(cmd, query1, query2);
	}
	
	// User의 주소 근처 동물병원, 동물약국 검색
	public ArrayList<HPReqDto> addressSearch(String cmd, String address) {
		AnimalDao animalDao = new AnimalDao();
		return animalDao.addressSearch(cmd, address);
	}
	
	// 메인페이지에 동물병원, 둥물약국 3개씩 보여주기
	public ArrayList<HPReqDto> indexSearch(String cmd, String address) {
		AnimalDao animalDao = new AnimalDao();
		return animalDao.indexSearch(cmd, address);
	}
	
	// 진료일지 등록시 병원 평점 변경
	public int hosptlRankUpdate(float rank, String address, String BIZPLC_NM) {
		AnimalDao animalDao = new AnimalDao();
		return animalDao.hosptlRankUpdate(rank, address, BIZPLC_NM);
	}
}
