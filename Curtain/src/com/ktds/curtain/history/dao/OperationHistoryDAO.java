package com.ktds.curtain.history.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class OperationHistoryDAO {

	public void insertHistory(OperationHistoryVO historyVO) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/operationHistory/insertHistory/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, historyVO.getIp());
			stmt.setString(2, historyVO.getEmail());
			stmt.setString(3, historyVO.getUrl());
			stmt.setString(4, historyVO.getActionCode());
			stmt.setString(5, historyVO.getDescription());
			stmt.setString(6, historyVO.getEtc());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
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
