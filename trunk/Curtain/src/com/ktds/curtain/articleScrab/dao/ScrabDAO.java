package com.ktds.curtain.articleScrab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.articleScrab.vo.ArticleScrabVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ScrabDAO {

	/**
	 * 스크랩 삭제
	 * @param scrabVO
	 */
	public void deleteScrab(ArticleScrabVO scrabVO) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/deleteDislike/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, scrabVO.getArticleId());
			stmt.setString(2, scrabVO.getEmail());
			stmt.setInt(3, scrabVO.getBoardId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}	

	}

	/**
	 * 스크랩 추가
	 * @param scrabVO
	 */
	public void insertScrab(ArticleScrabVO scrabVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/scrab/insertScrab/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, scrabVO.getArticleId());
			stmt.setString(2, scrabVO.getEmail());
			stmt.setInt(3, scrabVO.getBoardId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}	

	}

	/**
	 * 스크랩 여부(0 아니면 1)
	 * 
	 * @param scrabVO
	 * @return
	 */
	public int selectScrabCount(ArticleScrabVO scrabVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/scrab/selectScrabCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, scrabVO.getArticleId());
			stmt.setString(2, scrabVO.getEmail());
			stmt.setInt(3, scrabVO.getBoardId());

			rs = stmt.executeQuery();
			rs.next();

			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	public List<ArticleScrabVO> showArticleScrab(MemberVO stdMember, String boardId) {
	   
		loadOracleDriver();

	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      List<ArticleScrabVO> articleScrabs = new ArrayList<ArticleScrabVO>();
	      ArticleScrabVO articleScrab = null;

	      try {
	         conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

	         String query = XML.getNodeString("//query/scrab/showArticleScrab/text()");
	         stmt = conn.prepareStatement(query);
	         stmt.setString(1, stdMember.getEmail());
	         stmt.setInt(2, Integer.parseInt(boardId));
	         rs = stmt.executeQuery();

	         while (rs.next()) {
	        	 articleScrab = new ArticleScrabVO();
	        	 articleScrab.setArticleScrabId(rs.getInt("ARTICLE_SCRAB_ID"));
	        	 articleScrab.setArticleId(rs.getInt("ARTICLE_ID"));
	        	 articleScrab.setEmail(rs.getString("EMAIL"));
	        	 articleScrab.setScrabDate(rs.getString("SCRAB_DATE"));
	        	 articleScrab.setBoardId(rs.getInt("BOARD_ID"));
	        	 articleScrabs.add(articleScrab);
	         }

	         return articleScrabs;

	      } catch (SQLException e) {
	         closeDB(conn, stmt, rs);
	      }
	      return articleScrabs;
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
