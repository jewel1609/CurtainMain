package com.ktds.curtain.articleDislike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;



public class DislikeDAO {

	public int selectDislikeCount(ArticleDislikeVO dislikeVO) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/selectDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			stmt.setString(2, dislikeVO.getEmail());
			stmt.setInt(3, dislikeVO.getBoardId());

			rs = stmt.executeQuery();
			rs.next();

			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}
	}


	public void deleteDislike(ArticleDislikeVO dislikeVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/deleteDislike/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			stmt.setString(2, dislikeVO.getEmail());
			stmt.setInt(3, dislikeVO.getBoardId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}	
	}


	public void insertDislike(ArticleDislikeVO dislikeVO) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/insertDislike/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			stmt.setString(2, dislikeVO.getEmail());
			stmt.setInt(3, dislikeVO.getBoardId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
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
