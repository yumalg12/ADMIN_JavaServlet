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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ServletContext context = null;
	List<String> userList = new ArrayList<String>();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		context = getServletContext();

		String userID = request.getParameter("userID");
		String userPW = request.getParameter("PW");

		MemberVO memberVO = new MemberVO();
		memberVO.setUserID(userID);
		memberVO.setUserPW(userPW);

		MemberDAO dao = new MemberDAO();
		boolean isExisted = dao.isExisted(memberVO);

		if (isExisted) {
			HttpSession session = request.getSession(); // 새 세션 생성
			//로그인 세션 저장
			session.setAttribute("isLogin", true);
			session.setAttribute("login.id", userID);
			session.setAttribute("login.pw", userPW);
			
			//NEW 세션일 경우 접속자 목록에 추가
			LoginImpl loginUser = new LoginImpl(userID, userPW);
			if(session.isNew()) {
				session.setAttribute("loginUser", loginUser);
				userList.add(userID);
				context.setAttribute("userList", userList);
			}

			response.sendRedirect("main");
			return;

		} else {
			response.sendRedirect("./");

		}	}

}
