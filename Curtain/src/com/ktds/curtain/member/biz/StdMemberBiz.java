package com.ktds.curtain.member.biz;

import com.ktds.curtain.major.dao.MajorDAO;
import com.ktds.curtain.member.dao.StdMemberDAO;
import com.ktds.curtain.univ.dao.UnivDAO;

public class StdMemberBiz {
	private StdMemberDAO stdMemberDAO;
	private MajorDAO majorDAO;
	private UnivDAO univDAO;
	
	public StdMemberBiz(){
		stdMemberDAO = new StdMemberDAO();
		majorDAO = new MajorDAO();
		univDAO = new UnivDAO();
	}
	
	public void addStdMember(String inputUnivEmail, String inputPassword, String inputUnivName, String inputMajorName,
			String inputSecondEmail) {
		int majorId = majorDAO.getMajorIdByMajorName(inputMajorName);
		System.out.println("majorId StdMemberBiz : " + majorId);
		int majorGroupId = majorDAO.getMajorGroupIdByMajorId(majorId);
		System.out.println("majorGroupId StdMemberBiz : " + majorGroupId);
		int univId = univDAO.getUnivIdByUnivName(inputUnivName);
		System.out.println("univId StdMemberBiz : " + univId);
		
		stdMemberDAO.addStdMember(inputUnivEmail,univId,majorId,inputSecondEmail,inputPassword,majorGroupId);
		
	}

	public void modifyMemberInfo(String userEmail, String userNickName) {
		stdMemberDAO.updateMemberInfo(userEmail, userNickName);
	}

	public void modifyMemberPassword(String userPw) {
		stdMemberDAO.updateMemberPassword(userPw);
	}

}
