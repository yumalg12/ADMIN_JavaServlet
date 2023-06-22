package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String userID = "";		
		
		if(session != null) {
		userID = (String) session.getAttribute("login.id");
		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Main</title>\r\n"
				+ "<script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\r\n"
				+ "<script type='text/javascript'>setTimeout('history.go(0);', 5000);</script>"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<div class=\"contents\">\r\n"
				+ "			<div class=\"container\">\r\n"
				+ "				<h1>메인페이지</h1>\r\n"
				+ "<p>로그인 아이디: "+userID+"</p>"
				+ "<p>총 접속자 수: "+LoginImpl.total_user+"</p>"
				+ "<button onclick=\"location.href='show'\">내 정보 보기</button>"
				+ "<button onclick=\"location.href='member'\">회원목록 보기</button>"
				+ "<button onclick=\"location.href='logout'\">로그아웃</button>"
				+ "				<div class=\"item\">\r\n"
				+ "				<div>\r\n"
				+ "  <canvas id=\"myChart\"></canvas>\r\n"
				+ "</div>"
				);
				
		MemberDAO dao = new MemberDAO();
		dao.mainChart(response);
				
		out.print("</body></html>");
		} else {
			response.sendRedirect("login.html");
		}
	}

}
