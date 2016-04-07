package com.ktds.oph.reply.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.reply.vo.ReplyVO;
import com.ktds.oph.util.xml.XML;

public class ReplyDAO {

	public ReplyVO getClaimReplyInfoByReplyId(String replyId) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/reply/getClaimReplyInfoByReplyId/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1,Integer.parseInt(replyId));
			rs = stmt.executeQuery();
			ReplyVO replyVO = null;
			
			if(rs.next()){
				replyVO = new ReplyVO();
				replyVO.setReplyId(rs.getInt("REPLY_ID"));
				replyVO.setReplyDesc(rs.getString("REPLY_DESC"));
				replyVO.setReplyRegisterDate(rs.getString("REPLY_REGISTER_DATE"));
				replyVO.setReplyModifyDate(rs.getString("REPLY_MODIFY_DATE"));
				replyVO.setArticleId(rs.getInt("ARTICLE_ID"));
				replyVO.setParentReplyId(rs.getInt("PARENT_REPLY_ID"));
				replyVO.setReplyOrder(rs.getInt("REPLY_ORDER"));
				replyVO.setReplyLikes(rs.getInt("REPLY_LIKES"));
				replyVO.setBoardId(rs.getInt("BOARD_ID"));
				replyVO.setReplyDislikes(rs.getInt("REPLY_DISLIKES"));
				replyVO.setEmail(rs.getString("EMAIL"));
			}
			return replyVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, rs);
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

	public void deleteReply(String replyId) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/deleteReply/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(replyId));
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}
}
