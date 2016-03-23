package com.ktds.curtain.major.biz;

import com.ktds.curtain.major.dao.MajorDAO;

public class MajorBiz {
	private MajorDAO majorDAO;
	
	public MajorBiz(){
		majorDAO = new MajorDAO();
	}
	public String checkMajorName(String inputMajorName) {
		
		return majorDAO.checkMajorName(inputMajorName);
		
	}

}
