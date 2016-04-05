package com.ktds.oph.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.member.dao.Const;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.survey.vo.SurveySearchVO;
import com.ktds.oph.survey.vo.SurveyVO;
import com.ktds.oph.util.xml.XML;

public class SurveyDAO {

	private SurveyVO surveyVO;

	public SurveyDAO() {
		surveyVO = new SurveyVO();
	}



	public void insertSurvey(String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, String surveyDate, MemberVO member) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/insertSurvey/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, surveyTitle);
			stmt.setString(2, surveyAnswer1);
			stmt.setString(3, surveyAnswer2);
			stmt.setString(4, surveyAnswer3);
			stmt.setString(5, surveyAnswer4);
			stmt.setString(6, surveyDate);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}




	public void insertSurveyStats(int surveyId) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/insertSurveyStats/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,surveyId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}
	
	public int getSurveyId(String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, String surveyDate) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int surveyId = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/getSurveyId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, surveyTitle);
			stmt.setString(2, surveyAnswer1);
			stmt.setString(3, surveyAnswer2);
			stmt.setString(4, surveyAnswer3);
			stmt.setString(5, surveyAnswer4);
			stmt.setString(6, surveyDate);
			rs = stmt.executeQuery();
			rs.next();
			surveyId = rs.getInt(1);
			return surveyId;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
		
	}


	public int getAllSurveyCount() {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int surveyCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/survey/getAllSurveyCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			surveyCount = rs.getInt(1);
			
			return surveyCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}


	public List<SurveyVO> getAllSurvey(SurveySearchVO searchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/survey/getAllSurvey/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<SurveyVO> survey = new ArrayList<SurveyVO>();
			SurveyVO surveyVO = null;

			while ( rs.next() ) {
				surveyVO = new SurveyVO();
				surveyVO.setSurveyId(rs.getInt("SURVEY_ID"));
				surveyVO.setSurveyTitle(rs.getString("SURVEY_TITLE"));
				surveyVO.setSurveyAnswer1(rs.getString("SURVEY_ANSWER1"));
				surveyVO.setSurveyAnswer2(rs.getString("SURVEY_ANSWER2"));
				surveyVO.setSurveyAnswer3(rs.getString("SURVEY_ANSWER3"));
				surveyVO.setSurveyAnswer4(rs.getString("SURVEY_ANSWER4"));
				surveyVO.setSurveyDate(rs.getString("SURVEY_DATE"));
				survey.add(surveyVO);
			}

			return survey;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}




	public void deleteSurvey(String surveyId) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/deleteSurvey/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(surveyId));
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}




	public SurveyVO getSurveyInfoBySurveyId(String surveyId) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/getSurveyInfoBySurveyId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(surveyId));
			rs = stmt.executeQuery();
			SurveyVO surveyVO = null;
			if(rs.next()){
				surveyVO = new SurveyVO();
				surveyVO.setSurveyId(rs.getInt("SURVEY_ID"));
				surveyVO.setSurveyTitle(rs.getString("SURVEY_TITLE"));
				surveyVO.setSurveyAnswer1(rs.getString("SURVEY_ANSWER1"));
				surveyVO.setSurveyAnswer2(rs.getString("SURVEY_ANSWER2"));
				surveyVO.setSurveyAnswer3(rs.getString("SURVEY_ANSWER3"));
				surveyVO.setSurveyAnswer4(rs.getString("SURVEY_ANSWER4"));
				surveyVO.setSurveyDate(rs.getString("SURVEY_DATE"));
				
			}
			return surveyVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}



	public void modifyMember(String surveyId,String surveyTitle, String surveyAnswer1, String surveyAnswer2, String surveyAnswer3,
			String surveyAnswer4, MemberVO member) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/survey/modifySurvey/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1,surveyTitle);
			stmt.setString(2, surveyAnswer1);
			stmt.setString(3, surveyAnswer2);
			stmt.setString(4, surveyAnswer3);
			stmt.setString(5, surveyAnswer4);
			stmt.setInt(6, Integer.parseInt(surveyId));
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
