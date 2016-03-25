package com.ktds.curtain.major.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class MajorDAO {

	//학과 이름에 따른 학과 ID 받기
	public int getMajorIdByMajorName(String inputMajorName){
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int majorId = 0;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/major/getMajorIdByMajorName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputMajorName);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				majorId = rs.getInt("MAJOR_ID");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
			return majorId;
	}
	
	// 학과 Id에 따른 소계열 Id 찾기
	public int getMajorGroupIdByMajorId(int inputMajorId){
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int majorGroupId = 0;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/major/getMajorGroupIdByMajorId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, inputMajorId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				majorGroupId = rs.getInt("MAJOR_GROUP_ID");
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
			}
			return majorGroupId;
	}
	
	//입력받는 학과에 맞는 학과들 출력
	public String checkMajorName(String inputMajorName) {
		System.out.println("DAO : " + inputMajorName);
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String majorList = "";
		List<String> list = new ArrayList<String>();
		try {
		conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

		// articleId에 맞는 데이터 불러오기
		String query = XML.getNodeString("//query/major/checkMajorName/text()");
		stmt = conn.prepareStatement(query);
		stmt.setString(1, inputMajorName);
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("MAJOR_NAME"));
		}
		
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		for (int i = 0; i<list.size(); i++){
			majorList += list.get(i)+",";
		}
		return majorList;
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
