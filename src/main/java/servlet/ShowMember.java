package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String userID = "", userPW = "";
		Boolean isLogin = false;
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			isLogin = (Boolean) session.getAttribute("isLogin");
			if(isLogin == true) {
				userID = (String) session.getAttribute("login.id");
				userPW = (String) session.getAttribute("login.pw");
				out.print("<html><body>");
				out.print("<h1>회원정보 보기</h1>");
				out.print("<p>안녕하세요, "+userID+" 님!</p>");
				out.print("session ID: "+session.getId()+"<br>");
				out.print("ID: "+userID+"<br>");
				out.print("PW: "+userPW+"<br>");
				out.print("<a href='javascript:history.back(0)'>뒤로가기</a>");
				out.print("</body></html");
			} else {
				response.sendRedirect("./");
			}
		} else {
			response.sendRedirect("./");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
