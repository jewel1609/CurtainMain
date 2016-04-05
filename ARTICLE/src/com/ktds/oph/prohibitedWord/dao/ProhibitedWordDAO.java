package com.ktds.oph.prohibitedWord.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.xml.XML;

public class ProhibitedWordDAO {

	public void insertProhibitedWord(String prohibitedWordName, MemberVO member) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/prohibitedWord/insertProhibitedWord/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, prohibitedWordName);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}
	
	/**
	 * 오라클 드라이버
	 */
	private void loadOracleDriver() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * db접속 close
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
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
