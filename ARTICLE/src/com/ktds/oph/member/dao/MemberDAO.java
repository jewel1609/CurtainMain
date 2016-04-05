package com.ktds.oph.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ktds.oph.member.vo.MemberSearchVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.xml.XML;

public class MemberDAO {
	
	/**
	 * member 로그인하기
	 * @param memberVO
	 * @return
	 */
	public MemberVO getMemberByIdAndPassword(MemberVO memberVO) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getMemberByIdAndPassword/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, memberVO.getEmail());
			stmt.setString(2, memberVO.getPassword());
			
			rs = stmt.executeQuery();
			
			MemberVO validMember = null;

			if ( rs.next() ) {
				validMember = new MemberVO();
				validMember.setEmail( rs.getString("EMAIL") );
				validMember.setNickName( rs.getString("NICK_NAME") );
				validMember.setPassword( rs.getString("PASSWORD") );
				validMember.setMemberTypeId(rs.getInt("MEMBER_TYPE_ID"));
			}

			return validMember;
			
		}
		catch (SQLException e) {
			throw new RuntimeException();
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	
	
///////////////////////////////////////////커트으으은/////////////////////////
	public int getAllMemberCount() {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int memberCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/member/getAllMemberCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			memberCount = rs.getInt(1);
			
			return memberCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	

	public List<MemberVO> getAllMember(MemberSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/member/getAllMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<MemberVO> members = new ArrayList<MemberVO>();
			MemberVO memberVO = null;

			while ( rs.next() ) {
				memberVO = new MemberVO();
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setMemberTypeId(rs.getInt("MEMBER_TYPE_ID"));
				memberVO.setUnivId(rs.getInt("UNIV_ID"));
				memberVO.setMajorId(rs.getInt("MAJOR_ID"));
				memberVO.setSignupDate(rs.getString("SIGNUP_DATE"));
				memberVO.setNickName(rs.getString("NICK_NAME"));
				memberVO.setSecondEmail(rs.getString("SECOND_EMAIL"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setRankModifyDate(rs.getString("RANK_MODIFY_DATE"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				memberVO.setCompanyName(rs.getString("COMPANY_NAME"));
				memberVO.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				
				members.add(memberVO);
			}

			return members;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}



	public void deleteMember(String memberEmail) {
		this.loadOracleDriver();
		executeUpdateAndDeleteQuery(memberEmail, "deleteMember");
		System.out.println("DAO : " + memberEmail);
	}




	
	private void executeUpdateAndDeleteQuery(String memberEmail, String queryName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/member/"+queryName+"/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1,memberEmail);
			System.out.println("executeUpdateAndDeleteQuery : " +memberEmail);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}

	public MemberVO getMemberInfoByEmail(String memberEmail) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/member/getMemberInfoByEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1,memberEmail);
			rs = stmt.executeQuery();
			MemberVO memberVO = null;
			if(rs.next()){
				memberVO = new MemberVO();
				memberVO.setEmail(rs.getString("EMAIL"));
				memberVO.setMemberTypeId(rs.getInt("MEMBER_TYPE_ID"));
				memberVO.setUnivId(rs.getInt("UNIV_ID"));
				memberVO.setMajorId(rs.getInt("MAJOR_ID"));
				memberVO.setSignupDate(rs.getString("SIGNUP_DATE"));
				memberVO.setNickName(rs.getString("NICK_NAME"));
				memberVO.setSecondEmail(rs.getString("SECOND_EMAIL"));
				memberVO.setPoint(rs.getInt("POINT"));
				memberVO.setRankModifyDate(rs.getString("RANK_MODIFY_DATE"));
				memberVO.setPassword(rs.getString("PASSWORD"));
				memberVO.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				memberVO.setCompanyName(rs.getString("COMPANY_NAME"));
				memberVO.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				
			}
			return memberVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}



	public void modifyRankDate(String modifyMemberEmail) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String sysdate = mSimpleDateFormat.format ( currentTime );
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/member/modifyRankDate/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1,sysdate);
			stmt.setString(2,modifyMemberEmail);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}






	public void modifyMember(String modifyMemberEmail, String modifyMemberTypeId, String modifyMemberPoint,
			String modifyMemberPassword, MemberVO member) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/member/modifyMember/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(modifyMemberTypeId));
			stmt.setInt(2, Integer.parseInt(modifyMemberPoint));
			stmt.setString(3, modifyMemberPassword);
			stmt.setString(4, modifyMemberEmail);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}



	
	///////////////////////////////////////////
	/**
	 * 오라클 드라이버
	 */
	private void loadOracleDriver () {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	
	/**
	 * db접속 close
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	private void closeDB ( Connection conn, PreparedStatement stmt, ResultSet rs ) {
		
		if ( rs != null ) {
			try {
				rs.close();
			}
			catch ( SQLException e ) {}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			}
			catch ( SQLException e ) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			}
			catch ( SQLException e ) {}
		}
	}




}
