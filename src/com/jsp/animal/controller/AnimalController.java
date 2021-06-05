package com.jsp.animal.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.animal.service.AnimalService;

@WebServlet("/search")
public class AnimalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnimalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		animalAPI(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		animalAPI(request, response);
	}
	
	protected void animalAPI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		AnimalService service = new AnimalService();
		String viewPage = null;
		
		if (cmd.equals("animalhosptl")) { // 동물병원 검색결과 보여주기		
			service.searchHP(request, response); 
			viewPage = "search/animalHosptl.jsp";
		} else if (cmd.equals("animalpharmacy")) { // 동물약국 검색결과 보여주기
			service.searchHP(request, response); 
			viewPage = "search/animalPharmacy.jsp";		
		} else if (cmd.equals("animalfacilit")) { // 동물보호시설 검색결과 보여주기
			service.searchF(request, response); 
			viewPage = "search/animalFacilit.jsp";	
		} else if (cmd.equals("index")) { // 메인페이지에 내 주변 동물병원, 동물약국 3개 보여주기
			service.indexSearch(request, response); 	
			viewPage = "main.jsp";	
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
	    dis.forward(request, response);
	}

}
