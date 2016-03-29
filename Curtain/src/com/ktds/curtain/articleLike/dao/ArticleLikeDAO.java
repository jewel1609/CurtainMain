package com.ktds.curtain.articleLike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ArticleLikeDAO {

	/** 
	 * 내가 좋아요한 목록 보기 
	 * 게시판 아이디에 따라서
	 * @param stdMember
	 * @param boardId
	 * @return
	 */
	public List<ArticleLikeVO> showArticleLike(MemberVO stdMember, String boardId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleLikeVO> articleLikes = new ArrayList<ArticleLikeVO>();
		ArticleLikeVO articleLike = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/articleLikes/showArticleLike/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, stdMember.getEmail());
			stmt.setInt(2, Integer.parseInt(boardId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				articleLike = new ArticleLikeVO();
				articleLike.setArticleLikeId(rs.getInt("ARTICLE_LIKE_ID"));
				System.out.println(articleLike.getArticleLikeId());
				articleLike.setArticleId(rs.getInt("ARTICLE_ID"));
				articleLike.setEmail(rs.getString("EMAIL"));
				articleLike.setLikeDate(rs.getString("LIKE_DATE"));
				articleLike.setBoradId(rs.getInt("BOARD_ID"));
				
				articleLikes.add(articleLike);
			}

			return articleLikes;

		} catch (SQLException e) {
			closeDB(conn, stmt, rs);
		}
		return articleLikes;
	}
	

	public int selectLikeCount(ArticleLikeVO articleLikeVO, MemberVO member) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/articleLikes/selectLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleLikeVO.getArticleId());
			stmt.setString(2, member.getEmail());
			
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	public void deleteLike(ArticleLikeVO articleLikeVO, MemberVO member) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/articleLikes/deleteLike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, articleLikeVO.getArticleId());
			stmt.setString(2, member.getEmail());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	public void insertLike(ArticleLikeVO articleLikeVO, MemberVO member) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/articleLikes/insertLike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, articleLikeVO.getArticleId());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, articleLikeVO.getBoradId());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	

	/**
	 * 글 삭제시 좋아요한 글 삭제
	 * @param articleVO
	 */
	public void deleteArticle(ArticleVO articleVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/articleLikes/deleteArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
