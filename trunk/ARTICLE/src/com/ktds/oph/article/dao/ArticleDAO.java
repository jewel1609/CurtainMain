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
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.xml.XML;

public class ArticleDAO {

	/**
	 * 전체 게시글 보기
	 * @return
	 */
	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getAllArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			ArticleVO articleVO = null;

			while ( rs.next() ) {
				articleVO = new ArticleVO();
				articleVO.setArticleId( rs.getInt("ARTICLE_ID") );
				articleVO.setTitle( rs.getString("TITLE") );
				articleVO.setNickName(rs.getString("NICK_NAME"));
				articleVO.setHits(rs.getInt("HITS"));
				articleVO.setRecommends(rs.getInt("RECOMMENDS"));
				
				articles.add(articleVO);
			}

			return articles;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public int getAllArticleCount() {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int articleCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/article/getAllArticleCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			articleCount = rs.getInt(1);
			
			return articleCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		
	}
	
	/**
	 * 하나의 게시글 보기
	 * @param articleVO
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showDetail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			rs = stmt.executeQuery();
			
			ArticleVO article = null;

			if ( rs.next() ) {
				article = new ArticleVO();
				article.setArticleId( rs.getInt("ARTICLE_ID") );
				System.out.println(article.getArticleId());
				article.setTitle( rs.getString("TITLE") );
				article.setNickName(rs.getString("NICK_NAME"));
				article.setMemberId( rs.getString("MEMBER_ID"));
				article.setDescript(rs.getString("DESCRIPT"));
				article.setHits(rs.getInt("HITS"));
				article.setRecommends(rs.getInt("RECOMMENDS"));
			}

			return article;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}
	

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
	
	public int addRecommend(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/addRecommend/text()");
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
	
	public int doWrite(ArticleVO article, MemberVO member) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/doWrite/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, article.getTitle());
			stmt.setString(3, article.getDescript().replaceAll("\n", "<br/>"));
			
			int insertMember = stmt.executeUpdate();

			return latestArticleNo(conn);
			
		}
		catch (SQLException e) {
			throw new RuntimeException();
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
	
	public int doUpdate(ArticleVO changedArticle) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		int updateCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = "";
			
			if ( changedArticle.getTitle() != null && changedArticle.getTitle().length() > 0 ) {
				
				if ( changedArticle.getDescript() != null && changedArticle.getDescript().length() > 0 ) {
					// 둘 다 있는 경우
					query = XML.getNodeString("//query/article/updateArticle/text()");
				}
				else {
					// title만 있는 경우
					query = XML.getNodeString("//query/article/updateArticleOnlyTitle/text()");
				}
			}
			else {
				if ( changedArticle.getDescript() != null && changedArticle.getDescript().length() > 0 ) {
					// Description만 있는 경우	
				query = XML.getNodeString("//query/article/updateArticleOnlyDescription/text()");
				}		
			}
			
			stmt = conn.prepareStatement(query);
			
			if ( changedArticle.getTitle() != null && changedArticle.getTitle().length() > 0 ) {
				
				if ( changedArticle.getDescript() != null && changedArticle.getDescript().length() > 0 ) {
					// 둘 다 있는 경우
					stmt.setString(1, changedArticle.getTitle());
					stmt.setString(2, changedArticle.getDescript());
					stmt.setInt(3, changedArticle.getArticleId());
				}
				else {
					// title만 있는 경우
					stmt.setString(1, changedArticle.getTitle());
					stmt.setInt(2, changedArticle.getArticleId());
				}
			}
			else {
				if ( changedArticle.getDescript() != null && changedArticle.getDescript().length() > 0 ) {
					// Description만 있는 경우
					stmt.setString(1, changedArticle.getDescript());
					stmt.setInt(2, changedArticle.getArticleId());
				}		
			}
			
			updateCount = stmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		return updateCount;

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
