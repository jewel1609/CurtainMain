package com.ktds.oph.operationHistory.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.util.xml.XML;

public class OperationHistoryDAO {

public void insertHistory(OperationHistoryVO historyVO) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/operationHistory/insertHistory/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, historyVO.getIp());
			stmt.setString(2, historyVO.getUrl());
			stmt.setString(3, historyVO.getActionCode());
			stmt.setString(4, historyVO.getDescription());
			stmt.setString(5, historyVO.getEtc());
			stmt.setString(6, historyVO.getEmail());
			
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
