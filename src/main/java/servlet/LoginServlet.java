package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogin
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		String userID = request.getParameter("userID");
		String userPW = request.getParameter("PW");

		MemberVO memberVO = new MemberVO();
		memberVO.setUserID(userID);
		memberVO.setUserPW(userPW);

		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);

		if (result) {
			HttpSession session = request.getSession(); // 새 세션 생성

			session.setAttribute("isLogin", true);
			session.setAttribute("login.id", userID);
			session.setAttribute("login.pw", userPW);
			
			LoginImpl loginUser = new LoginImpl(userID, userPW);
			if(session.isNew()) {
				session.setAttribute("loginUser", loginUser);
			}

			response.sendRedirect("main");
			return;

		} else {
			response.sendRedirect("./login.html");

		}
	}
}
