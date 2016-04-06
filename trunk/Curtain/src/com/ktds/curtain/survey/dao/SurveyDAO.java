package com.ktds.curtain.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.survey.vo.SurveyStatsVO;
import com.ktds.curtain.survey.vo.SurveyVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class SurveyDAO {

	public SurveyVO selectTodaySurvey(String mTime) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		SurveyVO survey = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/survey/selectTodaySurvey/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, mTime);
			rs = stmt.executeQuery();

			if (rs.next()) {
				
				survey = new SurveyVO();
				
				survey.setSurveyId(rs.getInt("SURVEY_ID"));
				survey.setSurveyTitle(rs.getString("SURVEY_TITLE"));
				survey.setFirstAnswer(rs.getString("SURVEY_ANSWER1"));
				survey.setSecondAnswer(rs.getString("SURVEY_ANSWER2"));
				survey.setThirdAnswer(rs.getString("SURVEY_ANSWER3"));
				survey.setFourthAnswer(rs.getString("SURVEY_ANSWER4"));
				survey.setSurveyDate(rs.getString("SURVEY_DATE"));

			}

			return survey;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
		
	}
	
	public SurveyStatsVO selectSurveyStats(String mTime) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		SurveyStatsVO surveyStats = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/survey/selectSurveyStats/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, mTime);
			rs = stmt.executeQuery();

			if (rs.next()) {
				
				surveyStats = new SurveyStatsVO();
				
				surveyStats.setSurveyStatsId(rs.getInt("SURVEY_STATS_ID"));
				surveyStats.setSurveyId(rs.getInt("SURVEY_ID"));
				surveyStats.setFirstStats(rs.getInt("FIRST_STATS"));
				surveyStats.setSecondStats(rs.getInt("SECOND_STATS"));
				surveyStats.setThirdStats(rs.getInt("THIRD_STATS"));
				surveyStats.setFourthStats(rs.getInt("FOURTH_STATS"));

			}

			return surveyStats;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
		
	}
	
	public void upDateFirst(int surveyId) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/survey/upDateFirst/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setInt(1, surveyId);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}

	public void upDateSecond(int surveyId) {

		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/survey/upDateSecond/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setInt(1, surveyId);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}

	public void upDateThird(int surveyId) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/survey/upDateThird/text()");
			
			
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, surveyId);
			stmt.executeUpdate();
			

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}

	public void upDateFourth(int surveyId) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/survey/upDateFourth/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setInt(1, surveyId);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}
	
	
	public void upDateIsVote(String email, int surveyId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/survey/upDateIsVote/text()");
			stmt = conn.prepareStatement(query);
			
			//SQL Parameter Mapping
			stmt.setString(1, email);
			stmt.setInt(2, surveyId);
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}

	
	public int countSurveyFromRankModifyDate(MemberVO member) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			String query = XML.getNodeString("//query/survey/countSurveyFromRankModifyDate/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, member.getRankModifyDate().substring(2, 10));
			stmt.setString(2, member.getEmail());
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public int surveyCheck(String email, String mTime) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			String query = XML.getNodeString("//query/survey/surveyCheck/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, email);
			stmt.setString(2, mTime);
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
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
