package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;


/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		MemberDAO dao = new MemberDAO();
		
		//결과 표시부 시작		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Member Register</title>\r\n"
				+ "\r\n"
				+ "<link href=\"./css/bootstrap.css\" rel=\"stylesheet\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"./css/main.css\">\r\n"
				+ "\r\n"
				+ "<script src=\"http://dmaps.daum.net/map_js_init/postcode.v2.js\"></script>\r\n"
				+ "<script src=\"./script/execDaumPostcode.js\"></script>\r\n"
				+ "<script src=\"./script/fn_overlapped.js\"></script>\r\n"
				+ "\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<div class=\"contents\">\r\n"
				+ "<h1>회원가입</h1>\r\n"
				+ "<form name=\"Registerform\" method=\"get\">\r\n"
				+ "<div class=\"item\">\r\n"
				+ "    <label>아이디</label>\r\n"
				+ "    <input type=\"text\" class=\"form-control\" id=\"userID\" name=\"userID\" placeholder=\"user ID\" oninput=\"checkID(this.value)\" maxlength=\"12\"> \r\n"
				+ "    <input type=\"hidden\" id=\"userIDval\" name=\"userIDval\"> \r\n"
				+ "    <input type=\"button\" class=\"btn btn-outline-secondary\" id=\"checkIDdup\" value=\"중복확인\" onClick=\"fn_overlapped();\" style=\"display: none;\">\r\n"
				+ "    <span class=\"notice\" id=\"IDNotice\" style=\"display: none;\"> ※영문 소문자, 숫자로 이루어진 4~12자</span>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "    <div class=\"item\">\r\n"
				+ "    <label>비밀번호</label>\r\n"
				+ "    <input type=\"password\" class=\"form-control\" name=\"PW\" placeholder=\"password\" oninput=\"checkPWone(); checkPWtwo()\" maxlength=\"20\">\r\n"
				+ "    <input type=\"password\" class=\"form-control\" name=\"PWcheck\" style=\"display: none;\" placeholder=\"password check\" oninput=\"checkPWtwo()\" maxlength=\"20\">\r\n"
				+ "    <span class=\"notice\" id=\"PWNotice\" style=\"display: none;\"> ※영문 소문자, 숫자, 특수문자로 이루어진 4~20자</span>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "    <div class=\"item\">\r\n"
				+ "    <label>이름</label>\r\n"
				+ "    <input type=\"text\" class=\"form-control\" name=\"userName\" placeholder=\"user name\" oninput=\"checkName()\">\r\n"
				+ "    <span class=\"notice\" id=\"nameNotice\" style=\"display: none;\"> ※한글 실명 2~5자</span>\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "    <div class=\"item\">\r\n"
				+ "    <label>부서</label>\r\n"
				+ "    <select class=\"form-select\" name=\"dept\">");
		
		dao.registerForm(response);
		
		out.print("</select>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "<div class=\"item\">\r\n"
				+ "    <label>성별</label>\r\n"
				+ "    <span>여자</span><input type=\"radio\" name=\"gender\" value=\"female\">\r\n"
				+ "    <span> 남자</span><input type=\"radio\" name=\"gender\" value=\"male\">\r\n"
				+ "</div>    \r\n"
				+ "    \r\n"
				+ "    <div class=\"item\">\r\n"
				+ "    <label>생년월일</label>\r\n"
				+ "    <input type=\"date\" class=\"form-control\" name=\"birthDate\" onChange=\"checkBirth();\">\r\n"
				+ "    <input type=\"hidden\" name=\"bYear\" id=\"bYear\">\r\n"
				+ "    <input type=\"hidden\" name=\"bMon\" id=\"bMon\">\r\n"
				+ "    <input type=\"hidden\" name=\"bDay\" id=\"bDay\">\r\n"
				+ "    <span class=\"notice\" id=\"ageNotice\" style=\"display: none;\"> ※만 14세 미만은 가입할 수 없습니다.</span>\r\n"
				+ "    <span class=\"notice\" id=\"ageNotice2\" style=\"display: none;\"> ※미래 날짜는 선택할 수 없습니다.</span>\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "<div class=\"item\">\r\n"
				+ "    <label>전화번호</label> \r\n"
				+ "    <select class=\"form-select\" name=\"tel1\" onchange=\"phoneInput()\">\r\n"
				+ "		<option value=\"010\">010</option>\r\n"
				+ "		<option value=\"input\">직접 입력</option>\r\n"
				+ "	</select> \r\n"
				+ "	<input type=\"text\" class=\"form-control\" style=\"display: none; width: 80px;\" name=\"tel1\" oninput=\"checkPhoneOne(this.name)\" maxlength=\"3\"> \r\n"
				+ "	<span>-</span> \r\n"
				+ "	<input type=\"text\" class=\"form-control\" style=\"width: 80px;\" name=\"tel2\" oninput=\"checkPhone(this.name)\" maxlength=\"4\"> \r\n"
				+ "	<span>-</span> \r\n"
				+ "	<input type=\"text\" class=\"form-control\" style=\"width: 80px;\" name=\"tel3\" oninput=\"checkPhone(this.name)\" maxlength=\"4\"> \r\n"
				+ "	<br>\r\n"
				+ "	<label></label>\r\n"
				+ "	<input type=\"checkbox\" id=\"SMSYN\" name=\"SMSYN\" checked><span>SMS 수신 동의</span>\r\n"
				+ "</div>\r\n"
				+ "	\r\n"
				+ "	<div class=\"item\">\r\n"
				+ "    <label>이메일</label>\r\n"
				+ "	<input type=\"text\" class=\"form-control\" name=\"email1\" onChange=\"checkMail1()\")> \r\n"
				+ "	<span>@</span> \r\n"
				+ "	<select class=\"form-select\" name=\"email2\" style=\"width: 180px;\" onchange=\"emailInput()\">\r\n"
				+ "		<option value=\"naver.com\">naver.com</option>\r\n"
				+ "		<option value=\"gmail.com\">gmail.com</option>\r\n"
				+ "		<option value=\"input\">직접 입력</option>\r\n"
				+ "	</select>\r\n"
				+ "	<input type=\"text\" class=\"form-control\" style=\"display: none; width: 180px;\" name=\"email2\" oninput=\"checkMail2()\"> \r\n"
				+ "	<br>\r\n"
				+ "	<label></label>\r\n"
				+ "    <input type=\"checkbox\" id=\"emailYN\" name=\"emailYN\" checked><span>이메일 수신 동의</span>\r\n"
				+ "	</div>\r\n"
				+ "\r\n"
				+ "<div class=\"item\">\r\n"
				+ "    <label>주소</label>\r\n"
				+ "\r\n"
				+ "	<span class=\"addressdiv\" style=\"margin-left: 0px;\">우편번호: </span><input class=\"form-control\" type=\"text\" id=\"zipcode\" name=\"zipcode\" onInput= checkZipCode() maxlength=\"5\"> \r\n"
				+ "	<span class=\"btn btn-outline-secondary\" onClick=\"javascript:execDaumPostcode()\">우편번호 검색</span>\r\n"
				+ "	<br>\r\n"
				+ "	<label></label><span class=\"addressdiv\">지번 주소: </span><input class=\"form-control\" type=\"text\" id=\"jibunAddress\" name=\"jibunAddress\" style=\"width: 300px;\" onInput= checkAddress(this.name)>\r\n"
				+ "	<br>\r\n"
				+ "	<label></label><span class=\"addressdiv\">도로명 주소: </span><input class=\"form-control\" type=\"text\" id=\"roadAddress\" name=\"roadAddress\" style=\"width: 300px;\" onInput= checkAddress(this.name)>\r\n"
				+ "	<br>\r\n"
				+ "	<label></label><span class=\"addressdiv\">나머지 주소: </span><input class=\"form-control\" type=\"text\" name=\"namujiAddress\" style=\"width: 300px;\">\r\n"
				+ "</div>    \r\n"
				+ "<br>\r\n"
				+ "<div class=\"item\">\r\n"
				+ "    <input class=\"btn btn-primary\" type=\"button\" value=\"Register\" onClick=\"fn_sendMember()\">\r\n"
				+ "    <input class=\"btn btn-secondary\" type=\"reset\" value=\"Reset\">\r\n"
				+ "    <input type=\"hidden\" name=\"command\" value=\"addMember\">\r\n"
				+ "</div>\r\n"
				+ "</form>\r\n"
				+ "\r\n"
				+ "<script src=\"./script/register.js\"></script>\r\n"
				+ "\r\n"
				+ "<script type=\"text/javascript\">\r\n"
				+ "function fn_sendMember(){\r\n"
				+ "\r\n"
				+ "	if (!validateForm()){\r\n"
				+ "	} else if (validateForm()) {\r\n"
				+ "		Registerform.method = \"post\";\r\n"
				+ "		Registerform.action = \"member\";\r\n"
				+ "		alert(document.Registerform.userIDval.value + \" (\"+ document.Registerform.userName.value +\") 님의 가입이 완료되었습니다.\");\r\n"
				+ "		Registerform.submit();\r\n"
				+ "	}\r\n"
				+ "}\r\n"
				+ "</script>\r\n"
				+ "\r\n"
				+ "<script src=\"http://code.jquery.com/jquery-latest.js\"></script> \r\n"
				+ "</div>\r\n"
				+ "</body>\r\n"
				+ "</html>");
		//결과 표시부 끝

	}
	

}
