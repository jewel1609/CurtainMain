package com.ktds.curtain.member.biz;

import javax.servlet.http.HttpServletRequest;
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
	public void modifyMemberInfo(String userEmail, String userNickName, String email) {
		memberDAO.updateMemberInfo(userEmail, userNickName, email);
	}

	public void modifyMemberPassword(String userPw) {
		memberDAO.updateMemberPassword(userPw);
	}

	public boolean isExistMember(MemberVO member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		if(member.getEmail() == null && member.getEmail().length() == 0) {
			return false;
		}

		if(member.getPassword() == null || member.getPassword().length() == 0) {
			return false;
		}
		
		MemberVO currentMember = memberDAO.getMemberInfo(member);
		
		if ( currentMember == null) {
			return false;
		}
		else {
			session.setAttribute("_MEMBER_", currentMember);
			return true;
		}

	}

	public String getUnivName(int univId) {
		return memberDAO.getUnivName(univId);
	}

	public String getMajorName(int majorId) {
		return memberDAO.getMajorName(majorId);
	}

	public String getMajorGroupName(int majorGroupId) {
		return memberDAO.getMajorGroupName(majorGroupId);
	}
	
	public String getMemberRank(int memberTypeId) {
		return memberDAO.getMemberRank(memberTypeId);
	}

	
}
