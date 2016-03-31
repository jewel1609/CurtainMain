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
			stmt.setString(4, this.randomNickName());
			stmt.setString(5, inputSecondEmail);
			stmt.setString(6, inputPassword);
			stmt.setInt(7, majorGroupId);
			stmt.executeUpdate();
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
	}
	
	public void updateMemberInfo(String userEmail, String userNickName, String email) {
		
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
			stmt.setString(3, email);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	public void updateMemberPassword(String userNewPw, String email) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/updateMemberPassword/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, userNewPw);
			stmt.setString(2,  email);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	//글 게시에 따른 포인트 추가
	public void updatePoint(String email) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/updatePoint/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1,  email);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	public void updateMemberPasswordBySecondEmail(String email, String secondEmail, String userPw) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/stdMember/updateMemberPasswordBySecondEmail/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, userPw);
			stmt.setString(2, email);
			stmt.setString(3, secondEmail);
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
		
		String compNickName = inputCompName+"의 관리자";
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/compMember/addCompMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputCompEmail);
			stmt.setInt(2, 4);
			stmt.setString(3, compNickName);
			stmt.setString(4, inputSecondEmail);
			stmt.setString(5, inputPassword);
			stmt.setInt(6, inputPhoneNum);
			stmt.setString(7, inputCompName);
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
				member.setPassword(rs.getString("PASSWORD"));
				member.setPhoneNumber(rs.getInt("PHONE_NUMBER"));
				member.setCompanyName(rs.getString("COMPANY_NAME"));
				member.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				member.setSurveyId(rs.getInt("SURVEY_ID"));
				
				return member;
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		return null;
	}
	
	public String getUnivName(int univId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String univName = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getUnivName/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, univId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				univName = rs.getString("UNIV_NAME");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return univName;
		
	}
	
	public String getMajorName(int majorId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String majorName = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMajorName/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				majorName = rs.getString("MAJOR_NAME");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return majorName;
	}
	

	public void sendPassword(String email, String secondEmail) {


	}
	
	public String getMajorGroupName(int majorGroupId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String majorGroupName = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMajorGroupName/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				majorGroupName = rs.getString("MAJOR_GROUP_NAME");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return majorGroupName;
	}
	
	public String getMemberRank(int memberTypeId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String memberRank = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMemberRank/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, memberTypeId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				memberRank = rs.getString("MEMBER_RANK");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return memberRank;
		
	}
	
	public String randomNickName(){
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String randomNickName= "";
		int nounId = 0; 
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/randomNickName/text()");
			
			for( int i = 0; i < 3; i++){
				nounId = (int)(Math.random() * 3398 + 1);
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, nounId);
				
				rs = stmt.executeQuery();
				
				if( rs.next()) {
					randomNickName += rs.getString("NOUNNAME");
				}
				stmt.close();
				rs.close();
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return randomNickName;
	}
	

	public String getNickNameByEmail(String inputUnivName) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String nickName = "";
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getNickNameByEmail/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivName);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				nickName = rs.getString("NICK_NAME");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return nickName;
	}
	
	public void addPointByArticle(MemberVO member) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/addPointByArticle/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	public void modifyMemberTypeId(MemberVO member) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/modifyMemberTypeId/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	public int getMajorMemberCount(int majorGroupId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMajorMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
	}

	public int getUnivMemberCount(int univId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getUnivMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, univId);
			
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
	}

	public int getNoticeBoardMemberCount() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getNoticeBoardMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
		
	}

	public int getFreeBoardMemberCount() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getFreeBoardMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
	}

	public int getOneLayerCurtainMemberCount() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getOneLayerCurtainMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
	}

	public int getTwoLayerCurtainMemberCount() {
		
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getTwoLayerCurtainMemberCount/text()");
			
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			if( rs.next()) {
				count = rs.getInt("COUNT(EMAIL)");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return count;
	}

	
	
	public void secedeMemberFromEmail(String memberEmail) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/secedeMemberFromEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, memberEmail);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	

	public void minusPointByPromotion(MemberVO loginMember) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/minusPointByPromotion/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, loginMember.getEmail());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
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
