package com.ktds.oph.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.article.vo.ClaimArticleVO;
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.xml.XML;

public class ArticleDAO {


	public int addHit(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/addHit/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			
			int updateCount = stmt.executeUpdate();
			
			return updateCount;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}

	private int latestArticleNo(Connection conn) {
		
		loadOracleDriver();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = XML.getNodeString("//query/article/latestArticleNo/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			int articleId = rs.getInt(1);

			return articleId;
			
		}
		catch (SQLException e) {
			throw new RuntimeException();
		}
		finally {
			closeDB(null, stmt, rs);
		}
		
	}
	
	public int deleteArticle(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/deleteArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			
			int deleteCount = stmt.executeUpdate();
			
			return deleteCount;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}
	
	//////////////////////////Curtain 관리자//////////////////////////////////////

	public int getAllClaimArticleCount() {

		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int claimArticleCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/article/getAllClaimArticleCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			claimArticleCount = rs.getInt(1);
			
			return claimArticleCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	

	public int getAllClaimReplyCount() {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int claimReplyCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/article/getAllClaimReplyCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			claimReplyCount = rs.getInt(1);
			
			return claimReplyCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	

	public List<ClaimArticleVO> getAllClaimArticle(ArticleSearchVO searchVO) {

		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getAllClaimArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<ClaimArticleVO> claimArticles = new ArrayList<ClaimArticleVO>();
			ClaimArticleVO claimArticleVO = null;
			int claimCount = 0;
			int claimReplyCount = 0;
			while ( rs.next() ) {
				
				claimArticleVO = new ClaimArticleVO();
				claimArticleVO.setArticleClaimId(rs.getInt("ARTICLE_CLAIM_ID"));
				claimArticleVO.setEmail(rs.getString("EMAIL"));
				claimArticleVO.setArticleId(rs.getInt("ARTICLE_ID"));
				
				claimCount = getClaimArticleCountByArticleId(claimArticleVO.getArticleId());
				
				claimArticleVO.setReplyId(rs.getInt("REPLY_ID"));
				
				claimReplyCount = getAllClaimReplyCountByReplyId(claimArticleVO.getReplyId());
						
				claimArticleVO.setClaimDate(rs.getString("CLAIM_DATE"));
				claimArticleVO.setClaimText(rs.getString("CLAIM_TEXT"));
				
				claimArticleVO.setClaimCount(claimCount);
				claimArticleVO.setClaimReplyCount(claimReplyCount);
				
				claimArticles.add(claimArticleVO);
			}

			return claimArticles;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}

	//String 형 articleId
	public int getClaimArticleCountByArticleId(String articleId){
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int claimArticleCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/article/getClaimArticleCountByArticleId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(articleId));
			rs = stmt.executeQuery();
			
			rs.next();
			claimArticleCount = rs.getInt(1);
			
			return claimArticleCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}
	// int형 articleId
	public int getClaimArticleCountByArticleId(int articleId){
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int claimArticleCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/article/getClaimArticleCountByArticleId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,articleId);
			rs = stmt.executeQuery();
			
			rs.next();
			claimArticleCount = rs.getInt(1);
			
			return claimArticleCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}
	
	
		public int getAllClaimReplyCountByReplyId(int articleId){
			loadOracleDriver();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			int claimReplyCount = 0;
			
			try {
				conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
				
				String query =  XML.getNodeString("//query/reply/getAllClaimReplyCountByReplyId/text()");
				stmt = conn.prepareStatement(query);
				stmt.setInt(1,articleId);
				rs = stmt.executeQuery();
				
				rs.next();
				claimReplyCount = rs.getInt(1);
				
				return claimReplyCount;
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			finally {
				this.closeDB(conn, stmt, rs);
			}
		}
		
	
	public ArticleVO getClaimArticleInfoByArticleId(String articleId) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/article/getClaimArticleInfoByArticleId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(articleId));
			rs = stmt.executeQuery();
			ClaimArticleVO claimArticleVO = null;
			int claimCount = 0;
			if(rs.next()){
				claimCount = getClaimArticleCountByArticleId(articleId);
				claimArticleVO = new ClaimArticleVO();
				claimArticleVO.setArticleId(rs.getInt("ARTICLE_ID"));
				claimArticleVO.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				claimArticleVO.setArticleDesc(rs.getString("ARTICLE_DESC"));
				claimArticleVO.setArticleRegisterDate(rs.getString("ARTICLE_REGISTER_DATE"));
				claimArticleVO.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				claimArticleVO.setArticleTypeId(rs.getInt("ARTICLE_TYPE_ID"));
				claimArticleVO.setEmail(rs.getString("EMAIL"));
				claimArticleVO.setBoardId(rs.getInt("BOARD_ID"));
				claimArticleVO.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				claimArticleVO.setUnivId(rs.getInt("UNIV_ID"));
				claimArticleVO.setHits(rs.getInt("HITS"));
				claimArticleVO.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				claimArticleVO.setArticleDislike(rs.getInt("ARTICLE_DISLIKES"));
				claimArticleVO.setArticleScrab(rs.getInt("ARTICLE_SCRAB"));
				claimArticleVO.setClaimCount(claimCount);
			}
			return claimArticleVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
		}
	}



	///////////////////////////////////////////////////////////////////////////////
	

	/**
	 * 오라클 드라이버
	 */
	private void loadOracleDriver () {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	
	/**
	 * db접속 close
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	private void closeDB ( Connection conn, PreparedStatement stmt, ResultSet rs ) {
		
		if ( rs != null ) {
			try {
				rs.close();
			}
			catch ( SQLException e ) {}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			}
			catch ( SQLException e ) {}
		}
		if ( conn != null ) {
			try {
				conn.close();
			}
			catch ( SQLException e ) {}
		}
	}




}
