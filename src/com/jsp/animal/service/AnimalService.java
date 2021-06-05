package com.jsp.animal.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.search.AnimalDao;
import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;
import com.jsp.animal.util.Script;

public class AnimalService {
	private String cmd = null;
	
	// 유기보호시설 검색
	public void searchF(HttpServletRequest request, HttpServletResponse response) {
		cmd = request.getParameter("cmd");
		int facilitPage = Integer.parseInt(request.getParameter("page"));
		String facilitQuery1 = request.getParameter("query1");
		String facilitQuery2 = request.getParameter("query2");
		
		ArrayList<FReqDto> facilitanimals = new ArrayList<FReqDto>();
		AnimalDao facilitDao = new AnimalDao();
		facilitanimals = facilitDao.searchF(cmd, facilitPage); // 데이터베이스를 통해 데이터 검색
		int facilitdata = facilitDao.count(cmd, facilitQuery1, facilitQuery2); // 데이터베이스를 통해 총 데이터 개수 검색
		
		request.setAttribute("animals", facilitanimals); 		
		int facilitlastPage = (facilitdata - 1) / 30;
		request.setAttribute("lastPage", facilitlastPage);
	}
	
	// 동물병원, 동물약국 검색
	public void searchHP(HttpServletRequest request, HttpServletResponse response) {
		cmd = request.getParameter("cmd");
		int hosptlpage = Integer.parseInt(request.getParameter("page"));	
		String searchQuery1 = request.getParameter("query1");
		String searchQuery2 = request.getParameter("query2");
		
		AnimalDao hosptlDao = new AnimalDao();
		ArrayList<HPReqDto> hosptlAnimals = new ArrayList<HPReqDto>();
		
		hosptlAnimals = hosptlDao.searchHP(cmd, hosptlpage, searchQuery1, searchQuery2); // 데이터베이스를 통해 데이터 검색
		int hosptlData = hosptlDao.count(cmd, searchQuery1, searchQuery2); // 데이터베이스를 통해 총 데이터 개수 검색
		int hosptllastPage = (hosptlData - 1) / 30;
		
		request.setAttribute("animals", hosptlAnimals); 		
		request.setAttribute("lastPage", hosptllastPage);
		request.setAttribute("query1", searchQuery1);
		request.setAttribute("query2", searchQuery2);
	}
	
	// User의 주소 근처 동물병원, 동물약국 검색
	public void addressSearch(HttpServletRequest request, HttpServletResponse response) {
		cmd = request.getParameter("cmd");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			String address = "";
			if (user.getJibunAddress().contains("수원시") || user.getJibunAddress().contains("성남시") || user.getJibunAddress().contains("안양시") ||
					user.getJibunAddress().contains("안산시") || user.getJibunAddress().contains("용인시") || user.getJibunAddress().contains("고양시")) {
				String str[] = user.getJibunAddress().split(" ");
				address = str[3];
			} else {
				String str[] = user.getJibunAddress().split(" ");
				address = str[2];
			}
			
			ArrayList<HPReqDto> userAnimals = new ArrayList<HPReqDto>();
			AnimalDao animalDao = new AnimalDao();
			userAnimals = animalDao.addressSearch(cmd, address);
			
			if (cmd.equals("animalhosptl")) {
				request.setAttribute("cmd", true);
			}
			request.setAttribute("animals", userAnimals);
		} else {
			Script.lognError(response, "로그인을 해주세요");
		}
		
	}
	
	// User의 주소 근처 동물병원 이름 검색
	public void hosptlNameSearch(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		String address = "";
		String str[] = user.getJibunAddress().split(" ");
		address = str[1];
			
		ArrayList<HPReqDto> animalDto = new ArrayList<HPReqDto>();
		AnimalDao animalDao = new AnimalDao();
		animalDto = animalDao.addressSearch("animalhosptl", address);
				
		request.setAttribute("animalDto", animalDto);
		request.setAttribute("journalRecordForm", true);
		request.setAttribute("address", address);
	}
	
	// 메인페이지에 동물병원, 둥물약국 3개씩 보여주기
	public void indexSearch(HttpServletRequest request, HttpServletResponse responses) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		if (user != null) {
			String userAddress = "";
			if (user.getJibunAddress().contains("수원시") || user.getJibunAddress().contains("성남시") || user.getJibunAddress().contains("안양시") ||
					user.getJibunAddress().contains("안산시") || user.getJibunAddress().contains("용인시") || user.getJibunAddress().contains("고양시")) {
				String str[] = user.getJibunAddress().split(" ");
				userAddress = str[3];
			} else {
				String str[] = user.getJibunAddress().split(" ");
				userAddress = str[2];
			}
			ArrayList<HPReqDto> hosptls = new ArrayList<HPReqDto>();
			ArrayList<HPReqDto> pharmacys = new ArrayList<HPReqDto>();
			
			AnimalDao animalDao = new AnimalDao();
			hosptls = animalDao.indexSearch("animalhosptl", userAddress);
			pharmacys = animalDao.indexSearch("animalpharmacy", userAddress);
			
			request.setAttribute("hosptls", hosptls);		
			request.setAttribute("pharmacys", pharmacys);
		}
		
	}
	
	// 진료일지 등록시 병원 평점 변경
	public int hosptlRankUpdate(float rank, String address, String BIZPLC_NM) {
		AnimalDao animalDao = new AnimalDao();
		return animalDao.hosptlRankUpdate(rank, address, BIZPLC_NM);
	}
}
