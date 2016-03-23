package com.ktds.curtain.auth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ktds.curtain.auth.vo.MacVO;
import com.ktds.curtain.auth.web.SMTPAuthenticator;
import com.ktds.curtain.util.web.Const;
import com.ktds.curtain.util.xml.XML;

public class MacDAO {

	public boolean sendAuthEmailByUnivEmail(String inputUnivEmail, String macDesc) {
		
		//정보를 담기 위한 객체
		Properties p = new Properties();

		//SMTP 서버의 계정 설정
		//Naver와 연결할 경우 네이버 아이디 지정
		//Google과 연결할 경우 본인의 Gmail 주소
		p.put("mail.smtp.user", "kopseop@gmail.com");

		//SMTP 서버 정보 설정
		//네이버일 경우 smtp.naver.com
		//Google일 경우 smtp.gmail.com
		p.put("mail.smtp.host", "smtp.gmail.com");
		 

		p.put("mail.transport.protocol", "smtp");
		//아래 정보는 네이버와 구글이 동일하므로 수정하지 마세요.
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");

		
	    Authenticator auth = new SMTPAuthenticator();
	    Session ses = Session.getInstance(p, auth);

	    // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
	    ses.setDebug(true);
	        
	    // 메일의 내용을 담기 위한 객체
	    MimeMessage msg = new MimeMessage(ses);
	    try {
	    // 제목 설정
	    msg.setSubject("Curtain 인증번호입니다.");
	        
	    // 보내는 사람의 메일주소
	    Address fromAddr = new InternetAddress("kopseop@gmail.com");
	    msg.setFrom(fromAddr);
	        
	    // 받는 사람의 메일주소
	    Address toAddr = new InternetAddress(inputUnivEmail);
	    msg.addRecipient(Message.RecipientType.TO, toAddr);
	        
	    // 메시지 본문의 내용과 형식, 캐릭터 셋 설정
	    msg.setContent(macDesc, "text/html;charset=UTF-8");
	        
	    // 발송하기
	    Transport.send(msg);
	    System.out.println(msg + " 이메일 전송 성공 ");
	    return true;
		} catch (MessagingException e) {
			System.out.println("이메일 전송 실패.");
			return false;
		}
	}
	
	public void addMacInfo(String inputUnivEmail,String macDesc){
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);
			String query = XML.getNodeString("//query/mac/addMacInfo/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivEmail);
			stmt.setString(2, macDesc);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
	}
	
	public String getMacDescByStudentEmail(String inputUnivEmail) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String macDesc = "";
		try {
			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASSWORD);

			// articleId에 맞는 데이터 불러오기
			String query = XML.getNodeString("//query/mac/getMacDescByStudentEmail/text()");
			stmt = conn.prepareStatement(query);
			stmt.setString(1, inputUnivEmail);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				macDesc = rs.getString("MAC_DESC");
			}
			System.out.println("이메일에따른 MAC_DESC : " + macDesc);
			return macDesc;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		
	}
	
	public boolean checkAuthNumByUnivEmail(String inputNumberCheck, String getAuthNum) {
		
		if (inputNumberCheck.equals(getAuthNum)) {
				System.out.println("true");
				return true;
			}
		System.out.println("false");
		return false;
	}
	
	//랜덤 문자열 생성.
	public String makeRandomAuthNum(){
		Random rnd =new Random();
		char str;
		int num;
		String randomStr="";
		 
		for(int i=0;i<6;i++){
		    if(rnd.nextBoolean()){
		        str=((char)((int)(rnd.nextInt(26))+97));
		        randomStr += str;
		    }else{
		        num=rnd.nextInt(10); 
		        randomStr += num;
		    }
		}
		System.out.println("랜덤 문자열: " + randomStr);
		return randomStr;
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
