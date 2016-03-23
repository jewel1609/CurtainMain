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
			System.out.println("Univ List :  " + list);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		for (int i = 0; i<list.size(); i++){
			univList += list.get(i)+",";
		}
		System.out.println(univList);
		return univList;
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
