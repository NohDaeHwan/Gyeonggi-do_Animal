package com.jsp.animal.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.user.User;
import com.jsp.animal.service.AnimalService;
import com.jsp.animal.service.JournalService;
import com.jsp.animal.util.Script;

@WebServlet("/journal")
public class JournalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JournalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String viewPage = null;
		JournalService journalService = new JournalService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		if (user != null) { // User 세션이 있는지 확인
			if (cmd.equals("journalRecordForm")) { // 동물 진료 일지 작성 폼으로 이동		
				AnimalService service = new AnimalService();
				service.hosptlNameSearch(request, response); // 데이터베이스에서 User 주변 동물병원이름 가져오기		
				viewPage = "user/myPage.jsp";			
			} else if (cmd.equals("list")) { // 동물 진료 일지 검색
				journalService.reading(request, response); // 데이터베이스에서 User 동물 진료 일지 검색		
				viewPage = "user/myPage.jsp";
			} else if (cmd.equals("detail")) { // 동물 진료 일지 상세내용 검색
				journalService.detail(request, response); // 데이터베이스에서 동물 진료 일지 상세내용 검색
				viewPage = "user/myPage.jsp";
			}
			RequestDispatcher dis = request.getRequestDispatcher(viewPage); 
	  	    dis.forward(request, response);
		} else { // User 세션이 없으면 로그인 폼으로 이동
			Script.lognError(response, "로그인을 해주세요");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String cmd = request.getParameter("cmd");
		JournalService journalService = new JournalService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		if (user != null) { // User 세션이 있는지 확인
			if (cmd.equals("journalRecord")) { // 동물 진료 일지 저장
				journalService.writing(request, response);	
			}
		} else { // User 세션이 없으면 로그인 폼으로 이동
			Script.lognError(response, "로그인을 해주세요");
		}
	}

}
