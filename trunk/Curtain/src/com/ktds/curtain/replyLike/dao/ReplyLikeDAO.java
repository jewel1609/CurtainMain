package com.ktds.curtain.replyLike.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class ReplyLikeDAO {

	public List<ReplyLikeVO> getReplyLikes(int articleId,  MemberVO stdMember) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ReplyLikeVO> replyLikes = new ArrayList<ReplyLikeVO>();
		ReplyLikeVO replyLike = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/replyLike/getReplyLikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, stdMember.getEmail());
			stmt.setInt(2, articleId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				replyLike = new ReplyLikeVO();
				replyLike.setReplyId(rs.getInt("REPLY_ID"));
				
				replyLikes.add(replyLike);
			}

			return replyLikes;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	/**
	 * 리플 좋아요 삭제
	 * @param replyLikeVO
	 */
	public void deleteLike(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/replyLike/deleteLike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, replyLikeVO.getReplyId());
			stmt.setString(2, replyLikeVO.getEmail());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	/**
	 * 리플 좋아요 삽입
	 * @param replyLikeVO
	 */
	public void insertLike(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/replyLike/insertLike/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, replyLikeVO.getReplyId());
			stmt.setString(2, replyLikeVO.getEmail());
			stmt.setInt(3, replyLikeVO.getArticleId());

			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	/**
	 * 이미 좋아요 있는지 없는지 보기
	 * 1이면 있는것
	 * 0이면 없는것
	 * @param replyLikeVO
	 * @return
	 */
	public int selectLikeCount(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/replyLike/selectLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyLikeVO.getReplyId());
			stmt.setString(2, replyLikeVO.getEmail());
			
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
