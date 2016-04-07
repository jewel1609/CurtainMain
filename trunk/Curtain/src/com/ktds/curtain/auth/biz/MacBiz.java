package com.ktds.curtain.auth.biz;

import com.ktds.curtain.auth.dao.MacDAO;
import com.ktds.curtain.member.dao.MemberDAO;

public class MacBiz {
	private MacDAO macDAO;
	private MemberDAO memberDAO;
	
	public MacBiz(){
		macDAO = new MacDAO();
		memberDAO = new MemberDAO();
	}
	
	public boolean sendAuthEmailByUnivEmail(String inputUnivEmail) {
		String macDesc = "q";
		
		macDesc = macDAO.makeRandomAuthNum();
		//macDAO.addMacInfo(inputUnivEmail, macDesc);
		if(macDAO.sendAuthEmailByUnivEmail(inputUnivEmail,macDesc)){
			macDAO.addMacInfo(inputUnivEmail, macDesc);
			return true;
		}
		return false;
	}
	
	public boolean checkAuthNumByUnivEmail(String inputUnivEmail, String inputNumberCheck) {
		String getAuthNum ="";
		getAuthNum = macDAO.getMacDescByStudentEmail(inputUnivEmail);
		
		return macDAO.checkAuthNumByUnivEmail(inputNumberCheck, getAuthNum);
	}

	public boolean sendPasswordByEamil(String email, String secondEmail) {
		String randomPw = "";
		
		randomPw = macDAO.makeRandomAuthNum();
		
		if(macDAO.sendPasswordBySecondEmail(email, secondEmail, randomPw)){
			memberDAO.updateMemberPasswordBySecondEmail(email, secondEmail, randomPw);
			return true;
		}
		return false;
		
	}

	public void deleteAuthNumByUnivEmail(String inputUnivEmail) {
		macDAO.deleteAuthNumByUnivEmail(inputUnivEmail);
	}

}
