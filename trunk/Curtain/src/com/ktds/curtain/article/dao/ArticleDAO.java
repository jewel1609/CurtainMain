package com.ktds.curtain.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.member.vo.StdMemberVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ArticleDAO {

	// 학과
	public List<ArticleVO> showMajorArticle(StdMemberVO stdMember) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showMajorArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, 1);
			// stmt.setInt(2, stdMember.getMajorGroupId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}

	// 대학
	public List<ArticleVO> showUnivArticle(StdMemberVO stdMember) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showUnivArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, 2);
			// stmt.setInt(2, stdMember.getMajorGroupId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("UNIV_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}

	// 홍보
	public List<ArticleVO> showAdArticle(StdMemberVO stdMember, String boardId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showAdArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(boardId));
			// stmt.setInt(2, stdMember.getMajorGroupId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("UNIV_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}

	public int doWriteMajorArticle(ArticleVO article) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/doWriteMajorArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, article.getArticleTitle());
			stmt.setString(2, article.getArticleDesc());
			stmt.setInt(3, article.getArticleTypeId());
			stmt.setString(4, article.getStudentEmail());
			stmt.setInt(5, article.getBoardId());
			stmt.setInt(6, article.getMajorGroupId());
			int insertCount = stmt.executeUpdate();
			return insertCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		return 0;
	}

	public int doWriteUnivArticle(ArticleVO article) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/doWriteUnivArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, article.getArticleTitle());
			stmt.setString(2, article.getArticleDesc());
			stmt.setInt(3, article.getArticleTypeId());
			stmt.setString(4, article.getStudentEmail());
			stmt.setInt(5, article.getBoardId());
			stmt.setInt(6, article.getUnivId());
			int insertCount = stmt.executeUpdate();
			return insertCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		return 0;
	}

	public int doWriteAdArticle(ArticleVO article) {
		return 0;
	}

	public int doWriteSecretArticleOne(ArticleVO article) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int doWriteSecretArticleTwo(ArticleVO article) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int doWriteSecretArticleThree(ArticleVO article) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getArticleId() {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getArticleId/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			int articleId = 0;

			if (rs.next()) {
				articleId = rs.getInt("ARTICLE_ID");
			}

			return articleId;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	public List<ArticleVO> showLikesArticle(StdMemberVO stdMember) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showLikesArticle/text()");
			stmt = conn.prepareStatement(query);
			// stmt.setInt(1, stdMember.getMajorGroupId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
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
