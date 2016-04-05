package com.ktds.oph.questionAndAnswer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerSearchVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;
import com.ktds.oph.util.xml.XML;

public class QuestionAndAnswerDAO {
	
	public int getAllQuestionCount(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getAllQuestionCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			stmt.setString(2, searchVO.getSearchKeyword());
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}

	public int getAllQuestionCountByEmail(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getAllQuestionCountByEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}

	public int getAllQuestionCountByIsCheck(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getAllQuestionCountByIsCheck/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			rs = stmt.executeQuery();
			
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}
	
	public List<QuestionAndAnswerVO> getQuestions(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<QuestionAndAnswerVO> questions = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getQuestions/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			stmt.setString(2, searchVO.getSearchKeyword());
			stmt.setInt(3, searchVO.getEndIndex());
			stmt.setInt(4, searchVO.getStartIndex());
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
	
	public List<QuestionAndAnswerVO> getQuestionsByEmail(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<QuestionAndAnswerVO> questions = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getQuestionsByEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			stmt.setInt(2, searchVO.getEndIndex());
			stmt.setInt(3, searchVO.getStartIndex());
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

	public List<QuestionAndAnswerVO> getQuestionsByIsCheck(QuestionAndAnswerSearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<QuestionAndAnswerVO> questions = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getQuestionsByIsCheck/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, searchVO.getSearchKeyword());
			stmt.setInt(2, searchVO.getEndIndex());
			stmt.setInt(3, searchVO.getStartIndex());
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

	
	public QuestionAndAnswerVO getQuestionByQuestionId(int questionId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		QuestionAndAnswerVO question = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/getQuestionByQuestionId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, questionId);
			rs = stmt.executeQuery();
			
			if ( rs.next() ) {
				question = new QuestionAndAnswerVO();
				
				question.setAnswerDate(rs.getString("ANSWER_DATE"));
				question.setAnswerDescription(rs.getString("ANSWER_DESCRIPTION"));
				question.setEmail(rs.getString("EMAIL"));
				question.setIsChecked(rs.getString("IS_CHECKED"));
				question.setQuestionDate(rs.getString("QUESTION_DATE"));
				question.setQuestionDescription(rs.getString("QUESTION_DESCRIPTION"));
				question.setQuestionId(rs.getInt("QUESTION_ID"));
				question.setQuestionTitle(rs.getString("QUESTION_TITLE"));
			}
			
			return question;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}
	
	public void addNewAnswer(QuestionAndAnswerVO questionAndAnswerVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/question/addNewAnswer/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, questionAndAnswerVO.getAnswerDescription());
			stmt.setInt(2, questionAndAnswerVO.getQuestionId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
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
