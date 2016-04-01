package com.ktds.curtain.replyDislike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.replyDislike.vo.ReplyDislikeVO;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ReplyDislikeDAO {

	public List<ReplyDislikeVO> getReplyDislikes(int articleId, MemberVO stdMember) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ReplyDislikeVO> replyDislikes = new ArrayList<ReplyDislikeVO>();
		ReplyDislikeVO replyDislike = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/replyDislike/getReplyDislikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, stdMember.getEmail());
			stmt.setInt(2, articleId);
			rs = stmt.executeQuery();

			while(rs.next()) {
				replyDislike = new ReplyDislikeVO();
				replyDislike.setReplyId(rs.getInt("REPLY_ID"));
				
				replyDislikes.add(replyDislike);
			}

			return replyDislikes;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	/**
	 * 이미 싫어요 되어있는지 없는지 보기
	 * @param replyDislikeVO
	 * @return
	 */
	public int selectDislikeCount(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/replyDislike/selectDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyDislikeVO.getReplyId());
			stmt.setString(2, replyDislikeVO.getEmail());

			rs = stmt.executeQuery();
			rs.next();

			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}
	}

/**
 * 댓글 싫어요 삭제하기
 * @param replyDislikeVO
 */
	public void deleteDislike(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/replyDislike/deleteDislike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, replyDislikeVO.getReplyId());
			stmt.setString(2, replyDislikeVO.getEmail());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	/**
	 * 댓글 싫어요 삽입
	 * @param replyDislikeVO
	 */
	public void insertDislike(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/replyDislike/insertDislike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, replyDislikeVO.getReplyId());
			stmt.setString(2, replyDislikeVO.getEmail());
			stmt.setInt(3, replyDislikeVO.getArticleId());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
