package com.jsp.animal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.animal.domain.journal.Journal;
import com.jsp.animal.domain.journal.dto.JournalSaveReqDto;
import com.jsp.animal.domain.user.User;
import com.jsp.animal.search.AnimalDao;
import com.jsp.animal.search.dto.HPReqDto;
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
		JournalService journalService = new JournalService();
		
		// http://localhost:8080/animal/journal?cmd=journalRecordForm
		HttpSession session = request.getSession();
		if (cmd.equals("journalRecordForm")) { // 동물 진료 일지 작성 폼으로 이동
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				String address = "";
				String str[] = user.getJibunAddress().split(" ");
				address = str[1];
				
				ArrayList<HPReqDto> animalDto = new ArrayList<HPReqDto>();
				
				AnimalDao animalDao = new AnimalDao();
				animalDto = animalDao.addressSearch("animalhosptl", address);
				
				request.setAttribute("animalDto", animalDto);
				request.setAttribute("journalRecordForm", true);
				request.setAttribute("address", address);
				
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp"); 
		  	    dis.forward(request, response);
			} else { // User 세션이 없으면 로그인 폼으로 이동
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인을 해주세요');");
				out.print("window.location.href = '/animal/user?cmd=loginForm';");
				out.print("</script>");
				out.flush();
			} 
		} else if (cmd.equals("list")) { // 동물 진료 일지 검색
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				int page = Integer.parseInt(request.getParameter("page"));
				int userId = Integer.parseInt(request.getParameter("userId")); 
				
				List<Journal> journals = journalService.reading(page, userId); // 데이터베이스에서 동물 진료 일지 검색
				request.setAttribute("journals", journals);
				
				int journalCount = journalService.writingCount(userId); // 데이터베이스에서 동물 진료 일지 개수 검색
				int lastPage = (journalCount - 1) / 5;
				double currentPercent = (double) page / (lastPage) * 100;
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("currentPercent", currentPercent);	
				
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
			    dis.forward(request, response);
			} else { // User 세션이 없으면 로그인 폼으로 이동
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인을 해주세요');");
				out.print("window.location.href = '/animal/user?cmd=loginForm';");
				out.print("</script>");
				out.flush();
			}		
		} else if (cmd.equals("detail")) { // 동물 진료 일지 상세내용 검색
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				int id = Integer.parseInt(request.getParameter("id"));
				Journal journal = journalService.detail(id); // 데이터베이스에서 동물 진료 일지 상세내용 검색
				request.setAttribute("journal", journal);
				RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp");
			    dis.forward(request, response);
			} else { // User 세션이 없으면 로그인 폼으로 이동
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인을 해주세요');");
				out.print("window.location.href = '/animal/user?cmd=loginForm';");
				out.print("</script>");
				out.flush();
			}		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String cmd = request.getParameter("cmd");
		JournalService journalService = new JournalService();
		HttpSession session = request.getSession();
		
		if (cmd.equals("journalRecord")) { // 동물 진료 일지 저장
			User user = (User) session.getAttribute("User");
			if (user != null) { // User 세션이 있는지 확인
				int page = Integer.parseInt(request.getParameter("page"));
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

				int result = journalService.writing(dto);
				if (result == 1) { // 동물 진료 일지 작성 성공
					float average = journalService.rankAvg(dto.getVisitHosptl());
					
					AnimalService animalService = new AnimalService();
					animalService.hosptlRankUpdate(average, address, dto.getVisitHosptl());
					List<Journal> journals = journalService.reading(page, userId); // 데이터베이스에서 동물 진료 일지 검색
					request.setAttribute("journals", journals);
					
					int journalCount = journalService.writingCount(userId); // 데이터베이스에서 동물 진료 일지 개수 검색
					int lastPage = (journalCount - 1) / 10;
					double currentPercent = (double) page / (lastPage) * 100;
					request.setAttribute("lastPage", lastPage);
					request.setAttribute("currentPercent", currentPercent);	
					
					RequestDispatcher dis = request.getRequestDispatcher("user/myPage.jsp"); 
			  	    dis.forward(request, response);
				} else { // 동물 진료 일지 작성 실패
					Script.back(response, "일지 작성 실패");
				}
			} else { // User 세션이 없으면 로그인 폼으로 이동
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('로그인을 해주세요');");
				out.print("window.location.href = '/animal/user?cmd=loginForm';");
				out.print("</script>");
				out.flush();
			}
			
		}
	}

}
