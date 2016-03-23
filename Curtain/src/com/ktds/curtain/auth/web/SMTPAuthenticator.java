package com.ktds.curtain.auth.web;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator  extends javax.mail.Authenticator {
	private Password password;
    public PasswordAuthentication getPasswordAuthentication() {
        // 네이버나 Gmail 사용자 계정 설정.
        // Gmail의 경우 @gmail.com을 제외한 아이디만 입력한다.
    	password = new Password();
        return new PasswordAuthentication("kopseop", password.password());
    }
}
