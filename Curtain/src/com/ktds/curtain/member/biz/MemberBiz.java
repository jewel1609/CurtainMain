package com.ktds.curtain.member.biz;

import com.ktds.curtain.major.dao.MajorDAO;
import com.ktds.curtain.member.dao.MemberDAO;
import com.ktds.curtain.univ.dao.UnivDAO;

public class MemberBiz {
	private MemberDAO stdMemberDAO;
	private MajorDAO majorDAO;
	private UnivDAO univDAO;
	
	public MemberBiz(){
		stdMemberDAO = new MemberDAO();
		majorDAO = new MajorDAO();
		univDAO = new UnivDAO();
	}
	
	public void addStdMember(String inputUnivEmail, String inputPassword, String inputUnivName, String inputMajorName,
			String inputSecondEmail) {
		int majorId = majorDAO.getMajorIdByMajorName(inputMajorName);
		
		int majorGroupId = majorDAO.getMajorGroupIdByMajorId(majorId);
		
		int univId = univDAO.getUnivIdByUnivName(inputUnivName);
		
		stdMemberDAO.addStdMember(inputUnivEmail,univId,majorId,inputSecondEmail,inputPassword,majorGroupId);
		
	}
	public void addCompMember(String inputCompEmail, String inputPassword, String inputCompName, int inputPhoneNum,
			String inputSecondEmail) {
		stdMemberDAO.addCompMember(inputCompEmail,inputPassword,inputCompName,inputPhoneNum,inputSecondEmail);
	}
	public void modifyMemberInfo(String userEmail, String userNickName) {
		stdMemberDAO.updateMemberInfo(userEmail, userNickName);
	}

	public void modifyMemberPassword(String userPw) {
		stdMemberDAO.updateMemberPassword(userPw);
	}
}
