package com.ktds.curtain.univ.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class UnivDAO {

//	public String getAllUniv(){
//		
//	}
	
	public String checkUnivName(String inputUnivName) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String univList = "";
		List<String> list = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/univ/checkUnivName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivName);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString("UNIV_NAME"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		for (int i = 0; i<list.size(); i++){
			univList += list.get(i)+",";
		}
		return univList;
	}


	public int getUnivIdByUnivName(String inputUnivName) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int univId = 0;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// univName에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/univ/getUnivIdByUnivName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivName);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				univId = rs.getInt("UNIV_ID");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
			return univId;
	}
	

	public boolean isExistStdUnivEmail(String inputUnivEmail) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// univName에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/univ/isExistStdUnivEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivEmail);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				count++;
				return true;
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return false;
	}

	
	public String getUnivNameByUnivId(int univId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String univName = "";
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/univ/getUnivNameByUnivId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, univId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				univName = rs.getString("UNIV_NAME");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return univName;
	}
	
	
	public List<String> getUnivNamesByMajorGroupId(int majorGroupId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<String> univNames = new ArrayList<String>();
		String univName = "";
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/univ/getUnivNamesByMajorGroupId/text()");
			
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupId);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				univName = rs.getString("UNIV_NAME");
				univNames.add(univName);
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
		
		return univNames;
	
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
