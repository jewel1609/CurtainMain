package com.ktds.curtain.articleDislike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.member.vo.MemberVO;
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
	
	   public List<ArticleDislikeVO> showSecretArticleDislike(MemberVO stdMember, int boardId) {
		      
		      loadOracleDriver();

		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      List<ArticleDislikeVO> articleDislikes = new ArrayList<ArticleDislikeVO>();
		      ArticleDislikeVO articleDislike = null;

		      try {
		         conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

		         String query = XML.getNodeString("//query/dislike/showSecretArticleDisLike/text()");
		         stmt = conn.prepareStatement(query);
		         stmt.setString(1, "shinmi@curtain.ac.kr");
		         stmt.setInt(2, boardId);
		         rs = stmt.executeQuery();

		         while (rs.next()) {
		            articleDislike = new ArticleDislikeVO();
		            articleDislike.setArticleDislikeId(rs.getInt("ARTICLE_DISLIKE_ID"));
		            articleDislike.setArticleId(rs.getInt("ARTICLE_ID"));
		            articleDislike.setEmail(rs.getString("EMAIL"));
		            articleDislike.setDislikeDate(rs.getString("DISLIKE_DATE"));
		            articleDislike.setBoardId(rs.getInt("BOARD_ID"));
		            articleDislikes.add(articleDislike);
		         }

		         return articleDislikes;

		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         closeDB(conn, stmt, rs);
		      }
		      return articleDislikes;
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
