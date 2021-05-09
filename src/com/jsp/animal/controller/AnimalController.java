package com.jsp.animal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.animal.search.dto.FReqDto;
import com.jsp.animal.search.dto.HPReqDto;
import com.jsp.animal.service.AnimalService;

@WebServlet("/search")
public class AnimalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnimalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("animalhosptl") || cmd.equals("animalpharmacy")) { // 동물병원, 동물약국, 동물보호시설 검색결과 보여주기
			int page = Integer.parseInt(request.getParameter("page"));	
				
			ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
			
			AnimalService service = new AnimalService();
			animals = service.searchHP(cmd, page); // 데이터베이스를 통해 데이터 검색
			int data = service.count(cmd); // 데이터베이스를 통해 데이터 개수 검색
				
			System.out.println(data);
			request.setAttribute("animals", animals); 		
			int lastPage = (data - 1) / 30;
			request.setAttribute("lastPage", lastPage);
			
			if (cmd.equals("animalhosptl")) { // 동물병원 페이지 보여주기
				RequestDispatcher dis = request.getRequestDispatcher("search/animalHosptl.jsp"); 
	  	        dis.forward(request, response);	
			} else if (cmd.equals("animalpharmacy")) { // 동물약국 보여주기
				RequestDispatcher dis = request.getRequestDispatcher("search/animalPharmacy.jsp");
	  	        dis.forward(request, response);
			}
			
		} else if (cmd.equals("animalfacilit")) {
			int page = Integer.parseInt(request.getParameter("page"));
			
			ArrayList<FReqDto> animals = new ArrayList<FReqDto>();
			
			AnimalService service = new AnimalService();
			animals = service.searchF(cmd, page); // 데이터베이스를 통해 데이터 검색
			int data = service.count(cmd); // 데이터베이스를 통해 데이터 개수 검색
			
			System.out.println(data);
			request.setAttribute("animals", animals); 		
			int lastPage = (data - 1) / 30;
			request.setAttribute("lastPage", lastPage);
			
			RequestDispatcher dis = request.getRequestDispatcher("search/animalFacilit.jsp");
			dis.forward(request, response);		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
