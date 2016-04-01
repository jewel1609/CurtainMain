package com.ktds.oph.file.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.oph.file.vo.FileVO;
import com.ktds.oph.member.dao.Const;
import com.ktds.oph.util.xml.XML;

public class FileDAO {
	
	public int doWriteFile(FileVO file) {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);
			
			String query = XML.getNodeString("//query/file/doWriteFile/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, file.getArticleId());
			stmt.setString(2, file.getFileName());
			stmt.setString(3, file.getFileLocation());
			
			int insertCount = stmt.executeUpdate();
			
			return insertCount;
		} 
		catch ( SQLException e ) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
		}
		
	}
	
	public List<FileVO> showDetail(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<FileVO> files = new ArrayList<FileVO>();
		FileVO file = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/file/showDetail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			rs = stmt.executeQuery();
			
			while ( rs.next() ) {
				file = new FileVO();
				file.setArticleId(rs.getInt("FILE_ID"));
				file.setFileId(rs.getInt("ARTICLE_ID"));
				file.setFileName(rs.getString("FILE_NAME"));
				file.setFileLocation(rs.getString("FILE_LOCATION"));
				
				files.add(file);
			}
			return files;
			
		}
		catch (SQLException e) {
			throw new RuntimeException();
		}
		finally {
			closeDB(conn, stmt, null);
		}
	}
	
	public int deleteFile(int articleId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/file/deleteFile/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, articleId);
			
			int deleteCount = stmt.executeUpdate();
			
			return deleteCount;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			closeDB(conn, stmt, null);
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
