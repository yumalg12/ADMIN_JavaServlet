package servlet;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServletContext context;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		context = getServletContext();
		HttpSession session = request.getSession();
		String userID = request.getParameter("userID");
		session.invalidate();
		
		List<String> userList = (ArrayList<String>) context.getAttribute("userList");
		userList.remove(userID);
		context.removeAttribute("userList");
		context.setAttribute("userList", userList);
		
		System.out.print(userID + " 로그아웃");
		
		response.sendRedirect("./");
	}
}
