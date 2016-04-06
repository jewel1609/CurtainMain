package com.ktds.oph.history.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ktds.oph.history.vo.HistorySearchVO;
import com.ktds.oph.history.vo.HistoryVO;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.xml.XML;

public class HistoryDAO {

	public int getAllHistoryCount(HistoryVO historyVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int memberCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			

			
			String query = "SELECT COUNT(HISTORY_ID) "
			+ "FROM OPER_HIST "
			+ "WHERE CRTD_DATE "
			+"between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')";
			stmt = conn.prepareStatement(query);
			
//			stmt.setDate(1, (java.sql.Date) new SimpleDateFormat("YYYYMMDD").parse(historyVO.getStartDate()));
//			stmt.setDate(2, (java.sql.Date) new SimpleDateFormat("YYYYMMDD").parse(historyVO.getEndDate()));
			stmt.setString(1, historyVO.getStartDate());
			stmt.setString(2, historyVO.getEndDate());

			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			memberCount = rs.getInt(1);
			
			return memberCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	


	public List<HistoryVO> getAllHistory(HistorySearchVO historySearchVO, HistoryVO historyVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

//			String query = "SELECT HISTORY_ID, IP, CRTD_DATE, URL, ACTION_CODE, DESCRIPTION, ETC, EMAIL "
//					+ "FROM OPER_HIST "
//					+ "WHERE CRTD_DATE "
//					+"between to_date(?,'YYYY-MM-DD') and to_date(?,'YYYY-MM-DD')";
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, AB.* FROM	( "
					+"SELECT HISTORY_ID, IP, CRTD_DATE, URL, ACTION_CODE, DESCRIPTION, ETC, EMAIL "
					+"FROM OPER_HIST WHERE CRTD_DATE between to_date(?,'YYYY-MM-DD') and "
					+"to_date(?,'YYYY-MM-DD') ORDER BY HISTORY_ID DESC) AB "
					+"WHERE	ROWNUM <= ?) "
					+"WHERE	RNUM >= ?";
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, historyVO.getStartDate());
			stmt.setString(2, historyVO.getEndDate());
			stmt.setInt(3, historySearchVO.getEndIndex());
			stmt.setInt(4, historySearchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<HistoryVO> historys = new ArrayList<HistoryVO>();
			HistoryVO history = null;

			while ( rs.next() ) {
				history = new HistoryVO();
				history.setHistoryId(rs.getInt("HISTORY_ID"));
				history.setIp(rs.getString("IP"));
				history.setDate(rs.getString("CRTD_DATE"));
				history.setUrl(rs.getString("URL"));
				history.setActionCode(rs.getString("ACTION_CODE"));
				history.setDescription(rs.getString("DESCRIPTION"));
				history.setEtc(rs.getString("ETC"));
				history.setEmail(rs.getString("EMAIL"));
				historys.add(history);
			}

			return historys;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
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
