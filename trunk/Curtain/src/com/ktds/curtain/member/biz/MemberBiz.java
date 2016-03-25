package com.ktds.curtain.member.biz;

import javax.servlet.http.HttpSession;

import com.ktds.curtain.major.dao.MajorDAO;
import com.ktds.curtain.member.dao.MemberDAO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.univ.dao.UnivDAO;

public class MemberBiz {
	private MemberDAO memberDAO;
	private MajorDAO majorDAO;
	private UnivDAO univDAO;
	
	public MemberBiz(){
		memberDAO = new MemberDAO();
		majorDAO = new MajorDAO();
		univDAO = new UnivDAO();
	}
	
	public void addStdMember(String inputUnivEmail, String inputPassword, String inputUnivName, String inputMajorName,
			String inputSecondEmail) {
		int majorId = majorDAO.getMajorIdByMajorName(inputMajorName);
		
		int majorGroupId = majorDAO.getMajorGroupIdByMajorId(majorId);
		
		int univId = univDAO.getUnivIdByUnivName(inputUnivName);
		
		memberDAO.addStdMember(inputUnivEmail,univId,majorId,inputSecondEmail,inputPassword,majorGroupId);
		
	}
	public void addCompMember(String inputCompEmail, String inputPassword, String inputCompName, int inputPhoneNum,
			String inputSecondEmail) {
		memberDAO.addCompMember(inputCompEmail,inputPassword,inputCompName,inputPhoneNum,inputSecondEmail);
	}
	public void modifyMemberInfo(String userEmail, String userNickName) {
		memberDAO.updateMemberInfo(userEmail, userNickName);
	}

	public void modifyMemberPassword(String userPw) {
		memberDAO.updateMemberPassword(userPw);
	}

	public boolean isExistMember(MemberVO member,HttpSession session) {
		
		MemberVO sessionMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if(sessionMember.getEmail() == null || sessionMember.getEmail().length() == 0) {
			if(member.getEmail() == null && member.getEmail().length() == 0) {
				return false;
			}

			if(member.getPassword() == null || member.getPassword().length() == 0) {
				return false;
			}
			
			MemberVO currentMember = memberDAO.existMember(member);
			
			if ( currentMember == null) {
				return false;
			}
			else {
				session.setAttribute("_MEMBER_", currentMember);
			}
		}
		
		return true;
	}
}
