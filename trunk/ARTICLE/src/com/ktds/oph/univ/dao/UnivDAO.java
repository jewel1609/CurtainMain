package com.ktds.oph.univ.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.vo.UnivSearchVO;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.xml.XML;

public class UnivDAO {

	/**
	 * 전체 대학교 수
	 * @return
	 */
	public int getAllUnivCount() {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int memberCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/univ/getAllUnivCount/text()");
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

	public List<UnivVO> getAllUniv(UnivSearchVO univSearchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/univ/getAllUniv/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, univSearchVO.getEndIndex());
			stmt.setInt(2, univSearchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<UnivVO> univs = new ArrayList<UnivVO>();
			UnivVO univVO = null;

			while ( rs.next() ) {
				univVO = new UnivVO();
				univVO.setUnivId(rs.getInt("UNIV_ID"));
				univVO.setUnivName(rs.getString("UNIV_NAME"));
				univs.add(univVO);
			}

			return univs;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public void deleteUniv(String deleteUnivId) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/univ/deleteUniv/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(deleteUnivId));
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}
	
	public int updateUnivName(UnivVO univVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/univ/updateUnivName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, univVO.getUnivName());
			stmt.setInt(2,univVO.getUnivId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public int registerUniv(UnivVO univVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/univ/registerUniv/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, univVO.getUnivName());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public String getUnivName(UnivVO univVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/univ/getUnivName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, univVO.getUnivId());
			rs = stmt.executeQuery();

			rs.next();
			return rs.getString(1);
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
		
	}

	
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
