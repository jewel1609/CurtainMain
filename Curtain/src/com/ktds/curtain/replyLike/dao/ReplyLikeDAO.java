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
