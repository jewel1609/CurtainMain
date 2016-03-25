package com.ktds.curtain.qa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class QuestionAndAnswerDAO {

	public void registerQuestion(QuestionAndAnswerVO questionAndAnswerVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/qa/registerQuestion/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, questionAndAnswerVO.getQuestionTitle());
			stmt.setString(2, questionAndAnswerVO.getQuestionDescription());
			stmt.setString(3, questionAndAnswerVO.getEmail());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			closeDB(conn, stmt, null);
		}
	}
	
	public List<QuestionAndAnswerVO> getMyQuestionsByEmail(String email) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<QuestionAndAnswerVO> questions = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/qa/getMyQuestionsByEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			
			rs = stmt.executeQuery();
			
			questions = new ArrayList<QuestionAndAnswerVO> ();
			QuestionAndAnswerVO question;
			
			while( rs.next() ) {
				question = new QuestionAndAnswerVO();
				question.setQuestionTitle(rs.getString("QUESTION_TITLE"));
				question.setQuestionDescription(rs.getString("QUESTION_DESCRIPTION"));
				question.setQuestionDate(rs.getString("QUESTION_DATE"));
				question.setIsChecked(rs.getString("IS_CHECKED"));
				question.setAnswerDate(rs.getString("ANSWER_DATE"));
				question.setAnswerDescription(rs.getString("ANSWER_DESCRIPTION"));
				
				questions.add(question);
			}
			
			return questions;

		} catch (SQLException e) {
			closeDB(conn, stmt, rs);
		}
		
		return null;
		
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
