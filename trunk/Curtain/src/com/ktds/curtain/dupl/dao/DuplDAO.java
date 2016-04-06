package com.ktds.curtain.dupl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.dupl.vo.DuplVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class DuplDAO {

	public int isExistNickName(String inputUserNickName) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// univName에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/dupl/isExistNickName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUserNickName);
			rs = stmt.executeQuery();
			

			int duplCount = 0;
			rs.next();
			duplCount = rs.getInt(1);
			
			return duplCount;
			
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				closeDB(conn, stmt, rs);
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
