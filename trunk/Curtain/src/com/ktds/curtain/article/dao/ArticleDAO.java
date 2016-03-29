package com.ktds.curtain.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ArticleDAO {

	// 학과
	public List<ArticleVO> showMajorArticle(MemberVO stdMember) {

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
			stmt.setInt(1, stdMember.getMajorGroupId());
			System.out.println(stdMember.getNickName());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				article.setArticleDislikes(rs.getInt("ARTICLE_DISLIKES"));
				article.setLike(false);

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
	public List<ArticleVO> showUnivArticle(MemberVO stdMember) {
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
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("UNIV_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				article.setArticleDislikes(rs.getInt("ARTICLE_DISLIKES"));
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
	public List<ArticleVO> showAdArticle(MemberVO stdMember, String boardId) {
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
				article.setArticleDislikes(rs.getInt("ARTICLE_DISLIKES"));

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}

	//학과 게시판 글쓰기
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

			stmt.setString(4, article.getEmail());

			stmt.setInt(5, article.getBoardId());
			stmt.setInt(6, article.getMajorGroupId());
			int insertCount = stmt.executeUpdate();
			
			return getTheLatestArticleId(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		return 0;
	}
	
	
	// 가장 최근글 가져오기
	private int getTheLatestArticleId(Connection conn) {
		loadOracleDriver();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String query = XML.getNodeString("//query/article/getTheLatestArticleId/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			int getArticleId = rs.getInt(1);

			return getArticleId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(null, stmt, rs);
		}
	}

	// 대학 게시판 글쓰기

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
			stmt.setString(4, article.getEmail());
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

	// 홍보 게시판 글쓰기
	public int doWriteAdArticle(ArticleVO article) {
		return 0;
	}

	/**
	 * 가장 최근 게시물의 ArticleId 얻어오기
	 * @return
	 */
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

	/**
	 * 비밀게시판 리스트 얻어오기
	 * @param stdMember
	 * @return
	 */
	public List<ArticleVO> showSecretArticle(MemberVO stdMember) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showSecretArticle/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				article.setArticleDislikes(rs.getInt("ARTICLE_DISLIKES"));
				article.setArticleScrab(rs.getInt("ARTICLE_SCRAB"));
				article.setLike(false);
				article.setDislike(false);
				article.setScrab(false);

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}
	
	//내가좋아요한글
	public List<ArticleVO> showLikesArticle(MemberVO member) {

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
			stmt.setString(1, member.getEmail());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				//article.setLike(false);

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}
	
	//내가 스크랩한글
	public List<ArticleVO> showMyScrabArticle(MemberVO member) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showMyScrabArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				//article.setLike(false);

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}
	
	//내가 쓴글
	public List<ArticleVO> showMyWriteArticle(MemberVO member) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showMyWriteArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, member.getEmail());
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				//article.setLike(false);

				articles.add(article);
			}

			return articles;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articles;
	}
	
	/**
	 * 조회수 올리기
	 * @param articleVO
	 * @return
	 */
	public int hitsCount(ArticleVO article) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/hitsCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, article.getArticleId());
			int updateCount = stmt.executeUpdate();
			return updateCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		
		return 0;
	}
	
	/**
	 * 상세 글 내용 보기
	 * @param articleId
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArticleVO article = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showDetail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setEmail(rs.getString("EMAIL"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setBoardId(rs.getInt("MAJOR_GROUP_ID"));
				article.setMajorGroupId(rs.getInt("UNIV_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
			}

			return article;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return article;
	}

	/**
	 * 좋아요 -1
	 * @param articleLikeVO
	 */
	public void minusLikeCount(ArticleLikeVO articleLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/minusLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleLikeVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		
	}

	/**
	 * 좋아요 +1
	 * @param articleLikeVO
	 */
	public void plusLikeCount(ArticleLikeVO articleLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/plusLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleLikeVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		
	}
	
	/**
	 * 좋아요 수 가져오기
	 */
	public int getArticleLikes(ArticleLikeVO articleLikeVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getArticleLikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleLikeVO.getArticleId());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return 0;
	}
	
	/**
	 * 해당 article id 의 싫어요 수 가져오기 
	 * @param dislikeVO
	 * @return
	 */
	public int getArticleDislikes(ArticleDislikeVO dislikeVO) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getArticleDislikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return 0;
	}

	public void minusDislikeCount(ArticleDislikeVO dislikeVO) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/minusDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		
		
	}
	
	public void plusDislikeCount(ArticleDislikeVO dislikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/dislike/plusDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, dislikeVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			closeDB(conn, stmt, null);
		}
		
		
	}


	/**
	 * 가장 조회수 많은 게시글 정보 가져오기
	 * @param stdMember
	 * @return
	 */
	public ArticleVO showTopArticle(MemberVO stdMember) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArticleVO article = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/showTopArticle/text()");
			stmt = conn.prepareStatement(query);
			//stmt.setInt(1, boardId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setArticleTitle(rs.getString("ARTICLE_TITLE"));
				article.setArticleDesc(rs.getString("ARTICLE_DESC"));
				article.setArticleModifyDate(rs.getString("ARTICLE_MODIFY_DATE"));
				article.setArticleTypeName(rs.getString("ARTICLE_TYPE_NAME"));
				article.setNickName(rs.getString("NICK_NAME"));
				article.setBoardId(rs.getInt("BOARD_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setArticleLikes(rs.getInt("ARTICLE_LIKES"));
				article.setArticleDislikes(rs.getInt("ARTICLE_DISLIKES"));
				article.setArticleScrab(rs.getInt("ARTICLE_SCRAB"));
			}

			return article;

		} catch (SQLException e) {
			closeDB(conn, stmt, rs);
		}
		return article;
	}

	/**
	 * 글 삭제하기
	 * @param articleVO
	 * @return
	 */
	public int deleteArticle(ArticleVO articleVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/deleteArticle/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleVO.getArticleId());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		return 0;
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
