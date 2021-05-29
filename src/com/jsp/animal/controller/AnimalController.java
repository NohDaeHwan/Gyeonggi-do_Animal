package com.jsp.animal.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.search.AnimalDao;
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
		doAnimal(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAnimal(request, response);
	}
	
	protected void doAnimal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("animalhosptl") || cmd.equals("animalpharmacy")) { // 동물병원, 동물약국 검색결과 보여주기
			int page = Integer.parseInt(request.getParameter("page"));	
			String query1 = request.getParameter("query1");
			String query2 = request.getParameter("query2");
				
			ArrayList<HPReqDto> animals = new ArrayList<HPReqDto>();
			
			AnimalService service = new AnimalService();
			animals = service.searchHP(cmd, page, query1, query2); // 데이터베이스를 통해 데이터 검색
			int data = service.count(cmd, query1, query2); // 데이터베이스를 통해 데이터 개수 검색
				
			request.setAttribute("animals", animals); 		
			int lastPage = (data - 1) / 30;
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("query1", query1);
			request.setAttribute("query2", query2);
			
			if (cmd.equals("animalhosptl")) { // 동물병원 페이지 보여주기
				RequestDispatcher dis = request.getRequestDispatcher("search/animalHosptl.jsp"); 
	  	        dis.forward(request, response);	
			} else if (cmd.equals("animalpharmacy")) { // 동물약국 보여주기
				RequestDispatcher dis = request.getRequestDispatcher("search/animalPharmacy.jsp");
	  	        dis.forward(request, response);
			}
			
		} else if (cmd.equals("animalfacilit")) { // 동물보호시설 검색결과 보여주기
			int page = Integer.parseInt(request.getParameter("page"));
			String query1 = request.getParameter("query1");
			String query2 = request.getParameter("query2");
			
			ArrayList<FReqDto> animals = new ArrayList<FReqDto>();
			
			AnimalService service = new AnimalService();
			animals = service.searchF(cmd, page); // 데이터베이스를 통해 데이터 검색
			int data = service.count(cmd, query1, query2); // 데이터베이스를 통해 데이터 개수 검색
			
			System.out.println(data);
			request.setAttribute("animals", animals); 		
			int lastPage = (data - 1) / 30;
			request.setAttribute("lastPage", lastPage);
			
			RequestDispatcher dis = request.getRequestDispatcher("search/animalFacilit.jsp");
			dis.forward(request, response);		
		} else if (cmd.equals("index")) { // 메인페이지에 내 주변 동물병원, 동물약국 3개 보여주기
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
				
				ArrayList<HPReqDto> hosptls = new ArrayList<HPReqDto>();
				ArrayList<HPReqDto> pharmacys = new ArrayList<HPReqDto>();
				
				AnimalDao animalDao = new AnimalDao();
				hosptls = animalDao.indexSearch("animalhosptl", address);
				pharmacys = animalDao.indexSearch("animalpharmacy", address);
				
				request.setAttribute("hosptls", hosptls);		
				request.setAttribute("pharmacys", pharmacys);
				
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp"); 
	  	        dis.forward(request, response);	
			} else {
				RequestDispatcher dis = request.getRequestDispatcher("main.jsp"); 
	  	        dis.forward(request, response);	
			}
		}
	}

}
