package com.ktds.curtain.prohibitedWord.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ProhibitedWordDAO {

	/**
	 * 욕설 List 얻어오기
	 * @return
	 */
	public List<List<String>> getAllWords() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<String> words1 = new ArrayList<String>();
		List<String> words2 = new ArrayList<String>();
		List<String> words3 = new ArrayList<String>();
		List<String> words4 = new ArrayList<String>();
		List<String> words5 = new ArrayList<String>();

		List<List<String>> wordList = new ArrayList<List<String>>();
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/prohibitedWord/getAllWords/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

//			while (rs.next()) {
//				String word = rs.getString("PROHIBITED_WORD_NAME");
//				words.add(word);
//			}

			while (rs.next()) {
				
				int id = rs.getInt("PROHIBITED_WORD_ID");
				String word = rs.getString("PROHIBITED_WORD_NAME");
				
				if ( id >= 1 && id <= 500 ) {
					words1.add(word);
				}
				else if ( id >= 501 && id <= 1000) {
					words2.add(word);
				}
				else if ( id >= 1001 && id <= 1500){
					words3.add(word);
				}
				else if ( id >= 1501 && id <= 2000){
					words4.add(word);
				}
				else if ( id >= 2001){
					words5.add(word);
				}
			}
			wordList.add(words1);
			wordList.add(words2);
			wordList.add(words3);
			wordList.add(words4);
			wordList.add(words5);
			
			return wordList;

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
