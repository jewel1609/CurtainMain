package com.ktds.curtain.articleLike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.StdMemberVO;
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
	public List<ArticleLikeVO> showMajorArticleLike(StdMemberVO stdMember, String boardId) {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ArticleLikeVO> articleLikes = new ArrayList<ArticleLikeVO>();
		ArticleLikeVO articleLike = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/articleLikes/showMajorArticleLike/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, "test@smu.ac.kr");
			stmt.setInt(2, Integer.parseInt(boardId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				articleLike = new ArticleLikeVO();
				articleLike.setArticleLikeId(rs.getInt("ARTICLE_LIKE_ID"));
				articleLike.setArticleId(rs.getInt("ARTICLE_ID"));
				articleLike.setStudentEmail(rs.getString("STUDENT_EMAIL"));
				articleLike.setLikeDate(rs.getString("LIKE_DATE"));
				articleLike.setBoradId(rs.getInt("BOARD_ID"));
				articleLikes.add(articleLike);
			}

			return articleLikes;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, rs);
		}
		return articleLikes;
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
