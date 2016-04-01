package com.ktds.curtain.article.articleClaim.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.curtain.article.articleClaim.vo.ArticleClaimVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ArticleClaimDAO {

	public int doWriteClaim(ArticleClaimVO articleClaimVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/articleClaim/doWriteClaim/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, articleClaimVO.getEmail());
			stmt.setInt(2, articleClaimVO.getArticleId());
			stmt.setString(3, articleClaimVO.getClaimText());

			int insertCount = stmt.executeUpdate();
			
			return insertCount;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	public int doWriteReplyClaim(ArticleClaimVO articleClaimVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/articleClaim/doWriteReplyClaim/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, articleClaimVO.getEmail());
			stmt.setInt(2, articleClaimVO.getReplyId());
			stmt.setString(3, articleClaimVO.getClaimText());

			int insertCount = stmt.executeUpdate();
			
			return insertCount;

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
