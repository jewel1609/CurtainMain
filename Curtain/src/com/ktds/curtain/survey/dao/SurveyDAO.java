package com.ktds.curtain.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

			}

			return survey;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		
		return survey;
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
