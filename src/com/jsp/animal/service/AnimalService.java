package com.jsp.animal.service;

import java.util.ArrayList;

import com.jsp.animal.search.Animal;
import com.jsp.animal.search.AnimalDao;
import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;

public class AnimalService {
	
	public ArrayList<FReqDto> searchF(String cmd, int page) {
		AnimalDao dao = new AnimalDao();
		return dao.searchF(cmd, page);
	}
	
	public ArrayList<HPReqDto> searchHP(String cmd, int page) {
		AnimalDao dao = new AnimalDao();
		return dao.searchHP(cmd, page);
	}
	
	public int count(String cmd) {
		AnimalDao dao = new AnimalDao();
		return dao.count(cmd);
	}
	
	public ArrayList<Animal> indexSearch(String address) {
		AnimalDao animalDao = new AnimalDao();
		return animalDao.indexSearch(address);
	}
}
