package com.ktds.curtain.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class QuestionAndAnswerDAO {

	public void registerQuestionByStudent(QuestionAndAnswerVO questionAndAnswerVO, String email) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/qa/registerQuestionByStudent/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, questionAndAnswerVO.getQuestionTitle());
			stmt.setString(2, questionAndAnswerVO.getQuestionDescription());
			stmt.setString(3, email);
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			closeDB(conn, stmt, null);
		}
	}
	
	public void registerQuestionByCompany(QuestionAndAnswerVO questionAndAnswerVO, String email) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/qa/registerQuestionByCompany/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, questionAndAnswerVO.getQuestionTitle());
			stmt.setString(2, questionAndAnswerVO.getQuestionDescription());
			stmt.setString(3, email);
			
			stmt.executeUpdate();

		} catch (SQLException e) {
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
