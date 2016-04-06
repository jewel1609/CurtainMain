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
				major.setMajorGroupId(rs.getInt("MAJOR_GROUP_ID"));
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
	
	public int registerMajorGroup(MajorGroupVO majorGroupVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/registerMajorGroup/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorGroupVO.getMajorGroupName());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public String getMajorGroupName(MajorGroupVO majorGroupVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/major/getMajorGroupName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupVO.getMajorGroupId());
			rs = stmt.executeQuery();

			rs.next();
			return rs.getString(1);
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}


	public int updateMajorGroupName(MajorGroupVO majorGroupVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/updateMajorGroupName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorGroupVO.getMajorGroupName());
			stmt.setInt(2,majorGroupVO.getMajorGroupId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}
	

	public void deleteMajorGroups(int majorGroupId) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/deleteMajorGroups/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}


	public void deleteMajors(int majorGroupId) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/deleteMajors/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorGroupId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public int registerMajor(MajorVO majorVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/registerMajor/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorVO.getMajorName());
			stmt.setInt(2, majorVO.getMajorGroupId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public String getMajorName(MajorVO majorVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/major/getMajorName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorVO.getMajorId());
			rs = stmt.executeQuery();

			rs.next();
			return rs.getString(1);
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, rs);
		}
	}


	public int updateMajorName(MajorVO majorVO) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/updateMajorName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorVO.getMajorName());
			stmt.setInt(2,majorVO.getMajorId());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
	}


	public void deleteMajors2(int majorId) {
		this.loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query =  XML.getNodeString("//query/major/deleteMajors2/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, majorId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.closeDB(conn, stmt, null);
		}
		
	}
	

	public List<MajorGroupVO> getArticleByMajorGroupName(MajorGroupSearchVO majorSearchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/majorGroup/getArticleByMajorGroupName/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorSearchVO.getSearchKeyword());
			stmt.setInt(2, majorSearchVO.getEndIndex());
			stmt.setInt(3, majorSearchVO.getStartIndex());
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



	public int getArticleByMajorGroupNameCount(MajorGroupSearchVO majorSearchVO) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int majorGroupCount = 0;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/majorGroup/getArticleByMajorGroupNameCount/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, majorSearchVO.getSearchKeyword());
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
