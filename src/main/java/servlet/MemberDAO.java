package servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
