package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		
		if (command != null && command.equals("addMember")) {
			//가입 동작 추가
			String _userID = request.getParameter("userID");
			String _userPW = request.getParameter("userPW");
			String _userName = request.getParameter("userName");
			String _dept = request.getParameter("dept");
			String _gender = request.getParameter("gender");
			String _tel1 = request.getParameter("tel1");
			String _tel2 = request.getParameter("tel2");
			String _tel3 = request.getParameter("tel3");
			String _SMSYN = request.getParameter("SMSYN");
			String _email1 = request.getParameter("email1");
			String _email2 = request.getParameter("email2");
			String _emailYN = request.getParameter("emailYN");
			String _zipcode = request.getParameter("zipcode");
			String _roadaddress = request.getParameter("roadaddress");
			String _namujiaddress = request.getParameter("namujiaddress");
			String _bYear = request.getParameter("bYear");
			String _bMon = request.getParameter("bMon");
			String _bDay = request.getParameter("bDay");
			String _joinDate = request.getParameter("joinDate");
			String _delYN = request.getParameter("delYN");
			
			MemberVO vo = new MemberVO();
			
			vo.setUserID(_userID);
			vo.setUserPW(_userPW);
			vo.setUserName(_userName);
			vo.setDept(_dept);
			vo.setGender(_gender);
			vo.setTel1(_tel1);
			vo.setTel2(_tel2);
			vo.setTel3(_tel3);
			vo.setSMSYN(_SMSYN);
			vo.setEmail1(_email1);
			vo.setEmail2(_email2);
			vo.setEmailYN(_emailYN);
			vo.setZipcode(_zipcode);
			vo.setRoadaddress(_roadaddress);
			vo.setNamujiaddress(_namujiaddress);
			vo.setbYear(_bYear);
			vo.setbMon(_bMon);
			vo.setbDay(_bDay);
			vo.setJoinDate(_joinDate);
			vo.setDelYN(_delYN);
			
			dao.addMember(vo);

		} else if (command != null && command.equals("delMember")) {
			// 지우는 동작 추가해야 됨
			MemberVO vo = new MemberVO();

			vo.setDelYN("Y");
		}
		
		//결과 표시부 시작
		List<MemberVO> list = dao.listMembers();
		
		out.print("<html>"
				+ "<link href=\"./css/bootstrap.css\" rel=\"stylesheet\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"./css/main.css\">"
				+ "<body><h1>Register</h1>");
		out.print("<table border=1 cellspacing=0><tr aligh='center' bgcolor='lightgreen'>");
		out.print("<tr>\r\n"
				+ "		<th>Idx</th>\r\n"
				+ "		<th>아이디</th>\r\n"
				+ "		<th>이름</th>\r\n"
				+ "		<th>부서</th>\r\n"
				+ "		<th>성별</th>\r\n"
				+ "		<th>연락처</th>\r\n"
				+ "		<th>SMS 수신</th>\r\n"
				+ "		<th>이메일</th>\r\n"
				+ "		<th>이메일 수신</th>\r\n"
				+ "		<th>주소</th>\r\n"
				+ "		<th>생년월일</th>\r\n"
				+ "		<th>가입일</th>\r\n"
				+ "		<th>탈퇴</th>\r\n"
				+ "		<th>수정/제거</th>\r\n"
				+ "	</tr>");
		
		for (int i = 0; i < list.size(); i++) {
			MemberVO memberVO = list.get(i);
			String userID = memberVO.getUserID();
			String userName = memberVO.getUserName();
			String dept = memberVO.getDept();
			String gender = memberVO.getGender();
			String tel1 = memberVO.getTel1();
			String tel2 = memberVO.getTel2();
			String tel3 = memberVO.getTel3();
			String SMSYN = memberVO.getSMSYN();
			String email1 = memberVO.getEmail1();
			String email2 = memberVO.getEmail2();
			String emailYN = memberVO.getEmailYN();
			String zipcode = memberVO.getZipcode();
			String roadaddress = memberVO.getRoadaddress();
			String namujiaddress = memberVO.getNamujiaddress();
			String bYear = memberVO.getbYear();
			String bMon = memberVO.getbMon();
			String bDay = memberVO.getbDay();
			String joinDate = memberVO.getJoinDate();
			String delYN = memberVO.getDelYN();
			out.print("<tr>\r\n"
					+"<td>"+(i+1)+"</td>\r\n"
					+"<td>"+userID+"</td>\r\n"
					+"<td>"+userName+"</td>\r\n"
					+"<td>"+dept+"</td>\r\n"
					+"<td>"+gender+"</td>\r\n"
					+"<td>"+tel1+"-"+tel2+"-"+tel3+"</td>\r\n"
					+"<td>"+SMSYN+"</td>\r\n"
					+"<td>"+email1+"@"+email2+"</td>\r\n"
					+"<td>"+emailYN+"</td>\r\n"
					+"<td>(우)"+zipcode+" "+roadaddress+" "+namujiaddress+"</td>\r\n"
					+"<td>"+bYear+"년 "+bMon+"월 "+bDay+"일</td>\r\n"
					+"<td>"+joinDate+"</td>\r\n"
					+"<td>"+delYN+"</td>\r\n"
					+ "			<td><img class=\"imgbtn infobtn modbtn\" src=\"./repo/modify-icon.svg\" onclick=\"adminmodify(this)\">\r\n"
					+ "			<img class=\"imgbtn infobtn delbtn\" src=\"./repo/red-x-line-icon.svg\" onclick=\"admindel(this);\"></td>\r\n"
					+ "		</tr>");
		}
		out.print("</table><br><a href=\"/ADMIN_JavaServlet/register.html\">새 회원 등록하기</a>"
				+ "</body></html>");
		//결과 표시부 끝
		
	}

}
