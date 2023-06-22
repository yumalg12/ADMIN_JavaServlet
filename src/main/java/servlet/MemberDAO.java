package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.ArrayList;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			con = dataFactory.getConnection();

			String query = "SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, t_dept.dname, MEMBER_GENDER, TEL1, TEL2, TEL3, SMSSTS_YN, EMAIL1, EMAIL2, EMAILSTS_YN, ZIPCODE, ROADADDRESS, NAMUJIADDRESS, MEMBER_BIRTH_Y, MEMBER_BIRTH_M, MEMBER_BIRTH_D, JOINDATE, DEL_YN "
					+ "from `t_shopping_member` join `t_dept` on `t_shopping_member`.DEPTNO = `t_dept`.DEPTNO "
					//+ sqlSearch
					+"order by JOINDATE DESC;";
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String userID = rs.getString("MEMBER_ID");
				String userPW = rs.getString("MEMBER_PW");
				String userName = rs.getString("MEMBER_NAME");
				String dept = rs.getString("t_dept.dname");
				String gender = rs.getString("MEMBER_GENDER");
				String tel1 = rs.getString("TEL1");
				String tel2 = rs.getString("TEL2");
				String tel3 = rs.getString("TEL3");
				String SMSYN = rs.getString("SMSSTS_YN");
				String email1 = rs.getString("EMAIL1");
				String email2 = rs.getString("EMAIL2");
				String emailYN = rs.getString("EMAILSTS_YN");
				String zipcode = rs.getString("ZIPCODE");
				String roadaddress = rs.getString("ROADADDRESS");
				String namujiaddress = rs.getString("NAMUJIADDRESS");
				String bYear = rs.getString("MEMBER_BIRTH_Y");
				String bMon = rs.getString("MEMBER_BIRTH_M");
				String bDay = rs.getString("MEMBER_BIRTH_D");
				String joinDate = rs.getString("JOINDATE").substring(0,10);
				String delYN = rs.getString("DEL_YN");
				
				MemberVO vo = new MemberVO();
				
				vo.setUserID(userID);
				vo.setUserPW(userPW);
				vo.setUserName(userName);
				vo.setDept(dept);
				vo.setGender(gender);
				vo.setTel1(tel1);
				vo.setTel2(tel2);
				vo.setTel3(tel3);
				vo.setSMSYN(SMSYN);
				vo.setEmail1(email1);
				vo.setEmail2(email2);
				vo.setEmailYN(emailYN);
				vo.setZipcode(zipcode);
				vo.setRoadaddress(roadaddress);
				vo.setNamujiaddress(namujiaddress);
				vo.setbYear(bYear);
				vo.setbMon(bMon);
				vo.setbDay(bDay);
				vo.setJoinDate(joinDate);
				vo.setDelYN(delYN);
				
				list.add(vo);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public void addMember(MemberVO memberVO) {
		
		try {
			con = dataFactory.getConnection();
			
			String userID = memberVO.getUserID();
			String userPW = memberVO.getUserPW();
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
			String jibunaddress = memberVO.getJibunaddress();
			String namujiaddress = memberVO.getNamujiaddress();
			String bYear = memberVO.getbYear();
			String bMon = memberVO.getbMon();
			String bDay = memberVO.getbDay();
			
			String query = "insert into t_shopping_member"+
					"(MEMBER_ID, MEMBER_PW, MEMBER_NAME, DEPTNO, MEMBER_GENDER, "
					+"TEL1, TEL2, TEL3, SMSSTS_YN, "
					+"EMAIL1, EMAIL2, EMAILSTS_YN, "
					+"ZIPCODE, ROADADDRESS, JIBUNADDRESS, NAMUJIADDRESS, "
					+"MEMBER_BIRTH_Y, MEMBER_BIRTH_M, MEMBER_BIRTH_D) "+
					"values('"
					+userID+"','"+userPW+"','"+userName+"','"+dept+"','"+gender+"','"
					+tel1+"','"+tel2+"','"+tel3+"','"+SMSYN+"','"
					+email1+"','"+email2+"','"+emailYN+"','"
					+zipcode+"','"+roadaddress+"','"+jibunaddress+"','"+namujiaddress+"','"
					+bYear+"','"+bMon+"','"+bDay
					+"');";
			
			System.out.println("addMember(): "+query);
			
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delMember(String userID) {
		try {
			con = dataFactory.getConnection();
			
			String query = "delete from t_shopping_member where MEMBER_ID = '"+userID+"';";
			System.out.println("preparedStatement: "+query);
			
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void registerForm(HttpServletResponse response) {
		try {
			con = dataFactory.getConnection();
			
			String query = "SELECT * FROM `t_dept` ORDER BY DEPTNO ASC;";
			pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();
			PrintWriter out = response.getWriter();
			// 결과를 출력
			while (rs.next()) {
				Integer deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				out.print("<option value=\""+deptno+"\">"+dname+"</option>");
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getUserID();
		String pw = memberVO.getUserPW();
		try {
			con = dataFactory.getConnection();
			
			if (id != null || id != "") {				
			String query = "select if(count(*)=1, 'true', 'false') as result from t_shopping_member"
					+ " where MEMBER_ID = '"+id+"' and MEMBER_PW = '"+pw+"';";
			
			System.out.println("isExisted query: "+query);
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			result = Boolean.parseBoolean(rs.getString("result"));

			rs.close();
			pstmt.close();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public void mainChart(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		try {
			con = dataFactory.getConnection();

			String query = "SELECT DATE(JOINDATE) AS 'DATE', COUNT(*) AS 'COUNT' FROM t_shopping_member GROUP BY DATE(JOINDATE) ORDER BY DATE(JOINDATE);";
			
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			String g_labels = "'";
			String g_data = "";

			// 결과
			while (rs.next()) {
				String DATE = rs.getString("DATE");
				String COUNT = rs.getString("COUNT");

				g_labels = g_labels + DATE + "','";
				g_data = g_data + COUNT + ",";
			}
			
			out.print("\r\n<script type=\"text/javascript\">"
					+ "  const ctx = document.getElementById('myChart');"
					+ "  new Chart(ctx, {"
					+ "    type: 'line',"
					+ "    data: {    	"
					+ "      labels: ["+g_labels.substring(0, g_labels.length() - 2)+"],"
					+ "      datasets: [{"
					+ "        label: '일별 가입자 수',"
					+ "        data: ["+g_data.substring(0, g_data.length() - 1)+"],"
					+ "        borderWidth: 1,"
					+ "      }]"
					+ "    },"
					+ "    options: {"
					+ "      scales: {"
					+ "        y: {"
					+ "          beginAtZero: true"
					+ "        }"
					+ "      }"
					+ "    }"
					+ "  });"
					+ "</script>");
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
