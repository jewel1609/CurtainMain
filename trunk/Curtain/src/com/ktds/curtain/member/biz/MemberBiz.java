package com.ktds.curtain.member.biz;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.dao.ArticleDAO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.major.dao.MajorDAO;
import com.ktds.curtain.member.dao.MemberDAO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.prohibitedWord.dao.ProhibitedWordDAO;
import com.ktds.curtain.reply.vo.ReplyVO;
import com.ktds.curtain.univ.dao.UnivDAO;

public class MemberBiz {
	private MemberDAO memberDAO;
	private MajorDAO majorDAO;
	private UnivDAO univDAO;
	private ProhibitedWordDAO wordDAO;
	private ArticleDAO articleDAO;
	
	public MemberBiz(){
		memberDAO = new MemberDAO();
		majorDAO = new MajorDAO();
		univDAO = new UnivDAO();
		wordDAO = new ProhibitedWordDAO();
		articleDAO = new ArticleDAO();
	}
	
	public void addStdMember(String inputUnivEmail, String inputPassword, String inputUnivName, String inputMajorName,
			String inputSecondEmail) {
		
		memberDAO.addStdMember(inputUnivEmail
								,univDAO.getUnivIdByUnivName(inputUnivName)
								,majorDAO.getMajorIdByMajorName(inputMajorName)
								,inputSecondEmail,inputPassword
								,majorDAO.getMajorGroupIdByMajorId(majorDAO.getMajorIdByMajorName(inputMajorName)));
		
	}
	
	public String getNickNameByEmail(String inputUnivName){
		return memberDAO.getNickNameByEmail(inputUnivName);
	}
	
	public int getMajorIdByMajorName(String inputMajorName){
		return majorDAO.getMajorIdByMajorName(inputMajorName);
	}
	
	public int getMajorGroupIdByMajorId(int majorId){
		return majorDAO.getMajorGroupIdByMajorId(majorId);
	}
	
	
	public int getUnivIdByUnivName(String inputUnivName){
		return univDAO.getUnivIdByUnivName(inputUnivName);
	}
	
	public String getMajorGroupNameByMajorGroupId(int majorGroupId){
		return majorDAO.getMajorGroupNameByMajorGroupId(majorGroupId);
	}
	
	public String getUnivNameByUnivId(int univId){
		return univDAO.getUnivNameByUnivId(univId);
	}
	
	public String getDateTimeByEmail(String inputUnivEmail){
		
		return majorDAO.getDateTimeByEmail(inputUnivEmail);
	}
	
	public void addCompMember(String inputCompEmail, String inputPassword, String inputCompName, int inputPhoneNum,
			String inputSecondEmail) {
		memberDAO.addCompMember(inputCompEmail,inputPassword,inputCompName,inputPhoneNum,inputSecondEmail);
	}
	
	public void modifyMemberInfo(String userEmail, String userNickName, String email) {
		memberDAO.updateMemberInfo(userEmail, userNickName, email);
	}

	public void modifyMemberPassword(String userNewPw, String email) {
		System.out.println("testBIZ");
		memberDAO.updateMemberPassword(userNewPw, email);
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
			String univName = univDAO.getUnivNameByUnivId(member.getUnivId());
			String majorGroupName = majorDAO.getMajorGroupNameByMajorGroupId(member.getMajorGroupId());
			//욕설이 담김
			List<String> wordList = wordDAO.getAllWords();
			session.setAttribute("_MEMBER_", currentMember);
			session.setAttribute("_UNIVNAME_", univName);
			session.setAttribute("_MAJORGROUPNAME_", majorGroupName);
			session.setAttribute("_WORDLIST_", wordList);
			return true;
		}

	}

	//비밀번호 분실시 전송하는 메소드
	public void sendPassword(String email, String secondEmail) {

		memberDAO.sendPassword(email,secondEmail);
	}
	
	public void setCookie(MemberVO member, HttpServletResponse response) {
		Cookie autoLogin = new Cookie("autoLogin", "true");
		autoLogin.setMaxAge(30*24*60*60);
		response.addCookie(autoLogin);

		Cookie userId = new Cookie("userId", member.getEmail());
		userId.setMaxAge(30*24*60*60);
		response.addCookie(userId);
		
		Cookie password = new Cookie("userPassword", member.getPassword());
		password.setMaxAge(30*24*60*60);
		response.addCookie(password);
	}
	
	public void removeCookie(MemberVO member, HttpServletResponse response) {
		Cookie autoLogin = new Cookie("autoLogin", "");
		autoLogin.setMaxAge(0);
		response.addCookie(autoLogin);

		Cookie userId = new Cookie("userId", "");
		userId.setMaxAge(0);
		response.addCookie(userId);

		Cookie password = new Cookie("userPassword", "");
		password.setMaxAge(0);
		response.addCookie(password);
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
	
	public void addPointAndActivity (HttpServletRequest request, ArticleVO article, ReplyVO reply) {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");

		Calendar calendar = Calendar.getInstance();
		String currentDate = "";
		currentDate += calendar.get(Calendar.YEAR) + "/";
		currentDate = currentDate.substring(2, 5);
		currentDate += (calendar.get(Calendar.MONTH) + 1) + "/";
		currentDate += calendar.get(Calendar.DATE);
		
		int currentPoint = 0;
		int nextPoint = 0;
		
		if ( article != null ) {
			if(articleDAO.countTodayArticle(currentDate, member) < 5) {
				currentPoint = member.getPoint();
				nextPoint = currentPoint + 10;
				//memberDAO.addPoint(nextPoint);
			}
			
		}
		else if ( reply != null ) {
			
		}
		
	}


	
}
