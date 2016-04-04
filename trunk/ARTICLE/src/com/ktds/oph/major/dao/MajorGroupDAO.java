package com.ktds.oph.major.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.major.vo.MajorVO;
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.xml.XML;

public class MajorGroupDAO {

	public int getAllMajorGroupCount() {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int majorGroupCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/majorGroup/getAllMajorGroupCount/text()");
			stmt = conn.prepareStatement(query);
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			majorGroupCount = rs.getInt(1);
			
			return majorGroupCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}

	public List<MajorGroupVO> getAllMajorGroup(MajorGroupSearchVO majorSearchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/majorGroup/getAllMajorGroup/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorSearchVO.getEndIndex());
			stmt.setInt(2, majorSearchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<MajorGroupVO> majorGroups = new ArrayList<MajorGroupVO>();
			MajorGroupVO majorGroup = null;

			while ( rs.next() ) {
				majorGroup = new MajorGroupVO();
				majorGroup.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
				majorGroup.setMajorGroupName(rs.getString("MAJOR_GROUP_NAME"));
				majorGroups.add(majorGroup);
			}
			return majorGroups;
			
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public int getAllMajorCount(MajorVO majorVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int majorGroupCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/major/getAllMajorCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorVO.getMajorGroupId());
			// 물음표에 값 넣기 - 파라미터 매핑 (SQL Parameter Mapping)
			// 결과 받아오기
			rs = stmt.executeQuery();
			rs.next();
			majorGroupCount = rs.getInt(1);
			
			return majorGroupCount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public List<MajorVO> getAllMajor(MajorGroupSearchVO majorSearchVO, MajorVO majorVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/major/getAllMajor/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorVO.getMajorGroupId());
			stmt.setInt(2, majorSearchVO.getEndIndex());
			stmt.setInt(3, majorSearchVO.getStartIndex());
			rs = stmt.executeQuery();
			
			List<MajorVO> majors = new ArrayList<MajorVO>();
			MajorVO major = null;

			while ( rs.next() ) {
				major = new MajorVO();
				major.setMajorId(rs.getInt("MAJOR_ID"));
				major.setMajorName(rs.getString("MAJOR_NAME"));
				majors.add(major);
			}
			return majors;
			
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
