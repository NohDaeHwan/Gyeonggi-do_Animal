package com.jsp.animal.service;

import java.util.List;

import com.jsp.animal.domain.journal.Journal;
import com.jsp.animal.domain.journal.JournalDao;
import com.jsp.animal.domain.journal.dto.JournalSaveReqDto;

public class JournalService {
private JournalDao journalDao;
	
	public JournalService() {
		journalDao = new JournalDao();
	}
	
	// User 동물 진료 일지 작성
	public int writing(JournalSaveReqDto dto) { 
		int result = journalDao.save(dto);
		return result;
	}
	
	// User 동물 진료 일지 검색
	public List<Journal> reading(int page, int userId) {
		return journalDao.findAll(page, userId);
	}
	
	// User 동물 진료 일지 개수 가져오기
	public int writingCount(int userId) {
		return journalDao.count(userId);
	}
	
	// User 동물 진료 일지 상세보기
	public Journal detail(int id) {
		return journalDao.findById(id);
	}
	
	public float rankAvg(String visit) {
		return journalDao.rankAvg(visit);
	}
}
