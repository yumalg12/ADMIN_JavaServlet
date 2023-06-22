package servlet;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PrintWriter out = response.getWriter();
		
		context = getServletContext();
	
		HttpSession session = request.getSession(false);
		String userID = "";		
		
		if(session != null) {
		userID = (String) session.getAttribute("login.id");
		
		out.print("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>Main</title>"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>"
				+ "<script type='text/javascript'>setTimeout('history.go(0);', 10000);</script>"
				+ "</head>"
				+ "<body>"
				+ "	<div class=\"contents\">"
				+ "	<div class=\"container\">"
				+ "	<h1>메인페이지</h1>"
				+ "<p>로그인 아이디: "+userID+"</p>"
				+ "<p>접속자 목록 (총 "+LoginImpl.total_user+"명 접속)<ol>");
				
		List<String> list = (ArrayList<String>) context.getAttribute("userList");
		for (int i=0; i<list.size(); i++) {
			out.println("<li>"+list.get(i)+"</li>");
		}
		
		out.print("</ol></p>"
				+ "<button onclick=\"location.href='show'\">내 정보 보기</button>"
				+ "<button onclick=\"location.href='member'\">회원목록 보기</button>"
				+ "<button onclick=\"location.href='logout?userID="+userID+"'\">로그아웃</button>"
				+ "		<div class=\"item\">"
				+ "		<div>"
				+ "  <canvas id=\"myChart\"></canvas>"
				+ "</div></div>"
				);
				
		MemberDAO dao = new MemberDAO();
		dao.mainChart(response);
				
		out.print("</body></html>");
		} else {
			response.sendRedirect("./");
		}
	}

}
