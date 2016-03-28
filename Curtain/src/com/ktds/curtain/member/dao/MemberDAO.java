package com.ktds.curtain.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class MemberDAO {

	public void addStdMember(String inputUnivEmail, int univId, int majorId, String inputSecondEmail,
			String inputPassword, int majorGroupId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/addStdMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivEmail);
			stmt.setInt(2, univId);
			stmt.setInt(3, majorId);
			stmt.setString(4, inputSecondEmail);
			stmt.setString(5, inputPassword);
			stmt.setInt(6, majorGroupId);
			stmt.executeUpdate();
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
	}
	
	public void updateMemberInfo(String userEmail, String userNickName) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/updateMemberInfo/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, userEmail);
			stmt.setString(2, userNickName);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	public void updateMemberPassword(String userPw) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/updateMemberPassword/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, userPw);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}

	public void addCompMember(String inputCompEmail, String inputPassword, String inputCompName, int inputPhoneNum,
			String inputSecondEmail) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/compMember/addCompMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputCompEmail);
			stmt.setInt(2, 4);
			stmt.setString(3, inputSecondEmail);
			stmt.setString(4, inputPassword);
			stmt.setInt(5, inputPhoneNum);
			stmt.setString(6, inputCompName);
			stmt.executeUpdate();
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
	}
	
	public MemberVO getMemberInfo(MemberVO member) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMemberInfo/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				member.setEmail(rs.getString("EMAIL"));
				member.setMemberTypeId(rs.getInt("MEMBER_TYPE_ID"));
				member.setUnivId(rs.getInt("UNIV_ID"));
				member.setMajorId(rs.getInt("MAJOR_ID"));
				member.setSignupDate(rs.getString("SIGNUP_DATE"));
				member.setNickName(rs.getString("NICK_NAME"));
				member.setSecondEmail(rs.getString("SECOND_EMAIL"));
				member.setPoint(rs.getInt("POINT"));
				member.setRankModifyDate(rs.getString("RANK_MODIFY_DATE"));
				member.setActivity(rs.getInt("ACTIVITY"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhoneNumber(rs.getInt("PHONE_NUMBER"));
				member.setCompanyName(rs.getString("COMPANY_NAME"));
				member.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				
				return member;
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		return null;
	}
	
	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	

	
}