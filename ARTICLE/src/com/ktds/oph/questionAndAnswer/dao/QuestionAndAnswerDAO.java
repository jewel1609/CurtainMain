package com.ktds.oph.questionAndAnswer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;
import com.ktds.oph.util.xml.XML;

public class QuestionAndAnswerDAO {
	
	
	public List<QuestionAndAnswerVO> getAllQuestions() {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<QuestionAndAnswerVO> questions = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getAllQuestions/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			
			questions = new ArrayList<QuestionAndAnswerVO> ();
			QuestionAndAnswerVO question = null;
			
			while ( rs.next() ) {
				question = new QuestionAndAnswerVO();
				
				question.setAnswerDate(rs.getString("ANSWER_DATE"));
				question.setAnswerDescription(rs.getString("ANSWER_DESCRIPTION"));
				question.setEmail(rs.getString("EMAIL"));
				question.setIsChecked(rs.getString("IS_CHECKED"));
				question.setQuestionDate(rs.getString("QUESTION_DATE"));
				question.setQuestionDescription(rs.getString("QUESTION_DESCRIPTION"));
				question.setQuestionId(rs.getInt("QUESTION_ID"));
				question.setQuestionTitle(rs.getString("QUESTION_TITLE"));
				
				questions.add(question);
			}
			
			return questions;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
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


	public QuestionAndAnswerVO getQuestionByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
