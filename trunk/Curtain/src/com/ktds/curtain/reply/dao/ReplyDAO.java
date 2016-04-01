package com.ktds.curtain.reply.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.vo.ReplyVO;
import com.ktds.curtain.replyDislike.vo.ReplyDislikeVO;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;


public class ReplyDAO {

	public List<ReplyVO> getReplyListByArticleId(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/getReplyListByArticleId/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, articleId);

			rs = stmt.executeQuery();
			
			List<ReplyVO> replyList = new ArrayList<ReplyVO>();
			ReplyVO reply = null;
			
			while (rs.next()) {
				reply = new ReplyVO();
				reply.setReplyId(rs.getInt("REPLY_ID"));
				System.out.println(rs.getInt("REPLY_ID"));
				reply.setArticleId(rs.getInt("ARTICLE_ID"));
				reply.setNickName(rs.getString("NICK_NAME"));
				reply.setReplyDesc(rs.getString("REPLY_DESC"));
				reply.setParentReplyId(rs.getInt("PARENT_REPLY_ID"));
				reply.setReplyOrder(rs.getInt("REPLY_ORDER"));
				reply.setReplyLikes(rs.getInt("REPLY_LIKES"));
				reply.setReplyDislikes(rs.getInt("REPLY_DISLIKES"));
				
				replyList.add(reply);
			}
			
			return replyList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	// 글 등록
	public int insertReply(ReplyVO reply) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int insertCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/insertReply/text()");
			
			if ( reply.getParentReplyId() > 0 ) {
				query = XML.getNodeString("//query/reply/insertReReply/text()");
			}
			stmt = conn.prepareStatement(query);
			
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, reply.getArticleId());
			System.out.println(reply.getArticleId());
			stmt.setString(2, reply.getEmail());
			System.out.println(reply.getEmail());
			stmt.setString(3, reply.getReplyDesc().replaceAll("\n", "<br/>"));
			System.out.println(reply.getReplyDesc());
			stmt.setInt(4, reply.getBoardId());
			System.out.println(reply.getBoardId());
			
			//<br/>
			if ( reply.getParentReplyId() > 0 ) {
				stmt.setInt(5, reply.getParentReplyId());
				System.out.println(reply.getParentReplyId());
				stmt.setInt(6, reply.getReplyOrder());
				System.out.println(reply.getReplyOrder());
				
			}

			insertCount = stmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		return insertCount;
	}
	
	public int selectCountParentReplyID(ReplyVO reply) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/selectCountParentReplyID/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, reply.getParentReplyId());

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
	public int selectMaxOrderNoByParentReplyId(ReplyVO reply) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/selectMaxOrderNoByParentReplyId/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, reply.getReplyId());
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return rs.getInt(1);
			}
			else {
				return 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	public int selectLatestOrderNoByParentReplyId(ReplyVO reply) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
	
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/selectLatestOrderNoByParentReplyId/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, reply.getParentReplyId());

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
	public void updateOrderNoByGroupId(ReplyVO reply) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
	
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/reply/updateOrderNoByGroupId/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			stmt.setInt(1, reply.getReplyOrder());
			stmt.setInt(2, reply.getParentReplyId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}
	
	/**
	 * 게시글 삭제시 전체 댓글 삭제
	 * @param articleVO
	 */
	public void deleteReply(ArticleVO articleVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/deleteReply/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleVO.getArticleId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			closeDB(conn, stmt, null);
		}
		
	}
	
	public int countReplyFromRankModifyDate(MemberVO member) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			String query = XML.getNodeString("//query/reply/countReplyFromRankModifyDate/text()");
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, member.getRankModifyDate().substring(2, 10));
			stmt.setString(2, member.getEmail());
			
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
	 * 전체 댓글 좋아요에서 -1
	 * @param replyLikeVO
	 */
	public void minusLikeCount(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/minusLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyLikeVO.getReplyId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	/**
	 * 전체 댓글 좋아요에서 +1
	 * @param replyLikeVO
	 */
	public void plusLikeCount(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/plusLikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyLikeVO.getReplyId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	/**
	 * 그 댓글의 총 좋아요 수
	 * @param replyLikeVO
	 * @return
	 */
	public int getReplyLikes(ReplyLikeVO replyLikeVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/getReplyLikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyLikeVO.getReplyId());
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
	 * 댓글의 총 싫어요 중에 -1
	 * @param replyDislikeVO
	 */
	public void minusDislikeCount(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/minusDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyDislikeVO.getReplyId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}

	/**
	 * 댓글 총 싫어요 중에 +1
	 * @param replyDislikeVO
	 */
	public void plusDislikeCount(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/plusDislikeCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyDislikeVO.getReplyId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	/**
	 * 댓글의 총 싫어요 수
	 * @param replyDislikeVO
	 * @return
	 */
	public int getReplyDislikes(ReplyDislikeVO replyDislikeVO) {
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/reply/getReplyDislikes/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, replyDislikeVO.getReplyId());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
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
