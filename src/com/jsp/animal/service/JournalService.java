package com.jsp.animal.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.animal.domain.journal.Journal;
import com.jsp.animal.domain.journal.JournalDao;
import com.jsp.animal.domain.journal.dto.JournalSaveReqDto;
import com.jsp.animal.util.Script;

public class JournalService {
private JournalDao journalDao;
	
	public JournalService() {
		journalDao = new JournalDao();
	}
	
	// User 동물 진료 일지 작성
	public void writing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		int userId = Integer.parseInt(request.getParameter("userId"));
		String address = request.getParameter("address");
		String title = request.getParameter("title").replaceAll("<", "&lt;").replaceAll(">", "&gt;"); // <script>코드 방어
		Date treatDate = Date.valueOf(request.getParameter("treatDate"));
		String visitHosptl = request.getParameter("visit");
		int rank = Integer.parseInt(request.getParameter("rank"));
		String content = request.getParameter("content");
		
		JournalSaveReqDto dto = new JournalSaveReqDto();
		dto.setUserId(userId);
		dto.setTitle(title);
		dto.setTreatDate(treatDate);
		dto.setContent(content);
		dto.setVisitHosptl(visitHosptl);
		dto.setRank(rank);
		
		int result = journalDao.save(dto);
		
		if (result == 1) { // 동물 진료 일지 작성 성공		
			float average = journalDao.rankAvg(dto.getVisitHosptl()); // 동물진료일지 등급 평균 구하기
			
			AnimalService animalService = new AnimalService();
			animalService.hosptlRankUpdate(average, address, dto.getVisitHosptl()); // 진료일지 등록시 병원 평점 변경
			
			reading(request, response); // 데이터베이스에서 동물 진료 일지 검색
			
			RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp"); 
	  	    dis.forward(request, response);
		} else { // 동물 진료 일지 작성 실패
			Script.back(response, "일지 작성 실패");
		}
	}
	
	// User 동물 진료 일지 검색
	public void reading(HttpServletRequest request, HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page"));
		int userId = Integer.parseInt(request.getParameter("userId")); 
		
		List<Journal> journals = journalDao.findAll(page, userId); // 데이터베이스에서 동물 진료 일지 검색
		int journalCount = journalDao.count(userId); // 데이터베이스에서 동물 진료 일지 개수 검색
		
		int lastPage = (journalCount - 1) / 5;
		double currentPercent = (double) page / (lastPage) * 100;
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPercent", currentPercent);	
		request.setAttribute("journals", journals);
	}
	
	// User 동물 진료 일지 상세보기
	public void detail(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Journal journal = journalDao.findById(id); // 데이터베이스에서 동물 진료 일지 상세내용 검색
		request.setAttribute("journal", journal);
	}
	
}
