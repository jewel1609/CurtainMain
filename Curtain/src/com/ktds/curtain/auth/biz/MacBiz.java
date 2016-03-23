package com.ktds.curtain.auth.biz;

import com.ktds.curtain.auth.dao.MacDAO;

public class MacBiz {
	private MacDAO macDAO;
	
	public MacBiz(){
		macDAO = new MacDAO();
	}
	
	public boolean sendAuthEmailByUnivEmail(String inputUnivEmail) {
		String macDesc = "q";
		System.out.println(macDesc);
		
		macDesc = macDAO.makeRandomAuthNum();
		System.out.println("MacBiz" + macDesc);
		//macDAO.addMacInfo(inputUnivEmail, macDesc);
		if(macDAO.sendAuthEmailByUnivEmail(inputUnivEmail,macDesc)){
			macDAO.addMacInfo(inputUnivEmail, macDesc);
			return true;
		}
		return false;
	}
	
	public boolean checkAuthNumByUnivEmail(String inputUnivEmail, String inputNumberCheck) {
		System.out.println();
		String getAuthNum ="";
		getAuthNum = macDAO.getMacDescByStudentEmail(inputUnivEmail);
		System.out.println("Biz inputNum : " + inputNumberCheck);
		System.out.println("Biz getAuthNum : " + getAuthNum);
		
		return macDAO.checkAuthNumByUnivEmail(inputNumberCheck, getAuthNum);
	}

}
