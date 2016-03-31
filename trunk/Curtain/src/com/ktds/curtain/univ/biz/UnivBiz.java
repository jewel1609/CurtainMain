package com.ktds.curtain.univ.biz;

import java.util.List;

import com.ktds.curtain.univ.dao.UnivDAO;

public class UnivBiz {
	private UnivDAO univDAO;

	public UnivBiz() {
		univDAO = new UnivDAO();
	}

	public String checkUnivName(String inputUnivName) {
		return univDAO.checkUnivName(inputUnivName);
	}

	public boolean isExistStdUnivEmail(String inputUnivEmail) {

		return univDAO.isExistStdUnivEmail(inputUnivEmail);
	}
	
	public List<String> getUnivNameList(int majorGroupId){
		
		List<String> univNames = univDAO.getUnivNamesByMajorGroupId(majorGroupId);
		return univNames;
		
	}

}
