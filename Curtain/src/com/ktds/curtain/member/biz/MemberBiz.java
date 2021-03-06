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
import com.ktds.curtain.reply.dao.ReplyDAO;
import com.ktds.curtain.survey.dao.SurveyDAO;
import com.ktds.curtain.univ.dao.UnivDAO;

public class MemberBiz {
	private MemberDAO memberDAO;
	private MajorDAO majorDAO;
	private UnivDAO univDAO;
	private ProhibitedWordDAO wordDAO;
	private ArticleDAO articleDAO;
	private ReplyDAO replyDAO;
	private SurveyDAO surveyDAO;

	
	public MemberBiz(){
		memberDAO = new MemberDAO();
		majorDAO = new MajorDAO();
		univDAO = new UnivDAO();
		wordDAO = new ProhibitedWordDAO();
		articleDAO = new ArticleDAO();
		replyDAO = new ReplyDAO();
		surveyDAO = new SurveyDAO();

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
	
	public void addCompMember(String inputCompEmail, String inputPassword, String inputCompName, String inputPhoneNum,
			String inputSecondEmail) {
		memberDAO.addCompMember(inputCompEmail,inputPassword,inputCompName,inputPhoneNum,inputSecondEmail);
	}
	
	public void modifyMemberInfo(String userEmail, String userNickName, String email) {
		memberDAO.updateMemberInfo(userEmail, userNickName, email);
	}

	public void modifyMemberPassword(String userNewPw, String email) {
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
			
			currentMember.setMajorMemberCount(getMajorMemberCountByMajorGroupId(currentMember.getMajorGroupId()));
			currentMember.setUnivMemberCount(getUnivMemberCountByUnivId(currentMember.getUnivId()));
			currentMember.setNoticeBoardMemberCount(getNoticeBoardMemberCount());
			currentMember.setFreeBoardMemberCount(getFreeBoardMemberCount());
			currentMember.setOneLayerCurtainMemberCount(getOneLayerCurtainMemberCount());
			currentMember.setTwoLayerCurtainMemberCount(getTwoLayerCurtainMemberCount());
			
			String univName = univDAO.getUnivNameByUnivId(member.getUnivId());
			String majorGroupName = majorDAO.getMajorGroupNameByMajorGroupId(member.getMajorGroupId());
			
			// 욕설
			List<List<String>> wordList = wordDAO.getAllWords();
			
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

	/**
	 * @param request
	 * @param article 게시글을 쓰는 페이지가 아니라면 null 값을 넣어주면 됩니다.
	 */
	public void addPointAndModifyMemberType (ArticleVO article, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		addPoint(member, article);
		modifyMemberType(member, session);
	}
	
	private void addPoint (MemberVO member, ArticleVO article) {
		if (article == null) {
			return;
		}
		else {
			String todayDate = getTodayDateFormatDB();
			
			if(articleDAO.countTodayArticle(todayDate, member) <= 5) {
				memberDAO.addPointByArticle(member);
			}
		}
	}
	
	private String getTodayDateFormatDB() {
		Calendar calendar = Calendar.getInstance();
		String todayDate = "";
		todayDate += calendar.get(Calendar.YEAR) + "/";
		todayDate = todayDate.substring(2, 5);
		
		if((calendar.get(Calendar.MONTH) + 1) < 10) {
			todayDate += "0";
		}
		todayDate += (calendar.get(Calendar.MONTH) + 1) + "/";
		
		if(calendar.get(Calendar.DATE) < 10) {
			todayDate += "0";
		}
		todayDate += calendar.get(Calendar.DATE);
		
		return todayDate;
	}
	
	private void modifyMemberType(MemberVO member, HttpSession session) {
		
		if (member.getMemberTypeId() == 1 && isUpdateRankToTwo(member)) {
			memberDAO.modifyMemberTypeId(member);
			member.setMemberTypeId(member.getMemberTypeId() + 1);
			session.setAttribute("_MEMBER_", member);
		}
		else if (member.getMemberTypeId() == 2 && isUpdateRankToThree(member)) {
			memberDAO.modifyMemberTypeId(member);
			member.setMemberTypeId(member.getMemberTypeId() + 1);
			session.setAttribute("_MEMBER_", member);
		}
	}
	
	private boolean isUpdateRankToTwo (MemberVO member) {
		
		if(articleDAO.countArticleFromRankModifyDate(member) >= 5 
				&& replyDAO.countReplyFromRankModifyDate(member) >= 10 
				&& surveyDAO.countSurveyFromRankModifyDate(member) >= 7) {
			return true;
		}
		
		return false;
	}
	
	private boolean isUpdateRankToThree (MemberVO member) {
		
		if(articleDAO.countArticleFromRankModifyDate(member) >= 10
				&& replyDAO.countReplyFromRankModifyDate(member) >= 20 
				&& surveyDAO.countSurveyFromRankModifyDate(member) >= 14) {
			return true;
		}
		
		return false;
	}

	public int countArticle(MemberVO member) {
		return articleDAO.countArticleFromRankModifyDate(member);
	}

	public int countReply(MemberVO member) {
		return replyDAO.countReplyFromRankModifyDate(member);
	}

	public int countSurvey(MemberVO member) {
		return surveyDAO.countSurveyFromRankModifyDate(member);
	}

	public int getMajorMemberCountByMajorGroupId(int majorGroupId) {
		return memberDAO.getMajorMemberCount(majorGroupId);
	}

	public int getUnivMemberCountByUnivId(int univId) {
		return memberDAO.getUnivMemberCount(univId);
	}

	public int getNoticeBoardMemberCount() {
		return memberDAO.getNoticeBoardMemberCount();
	}

	public int getFreeBoardMemberCount() {
		return memberDAO.getFreeBoardMemberCount();
	}

	public int getOneLayerCurtainMemberCount() {
		return memberDAO.getOneLayerCurtainMemberCount();
	}

	public int getTwoLayerCurtainMemberCount() {
		return memberDAO.getTwoLayerCurtainMemberCount();
	}


	public void secedeMember(String memberEmail) {
		memberDAO.secedeMemberFromEmail(memberEmail);
	}

	public void minusPointByPromotion(MemberVO loginMember, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		if ( memberDAO.minusPointByPromotion(loginMember) > 0) {
			member.setPoint(getPointbyEmail(member));
			session.setAttribute("_MEMBER_", member);
		}
	}

	public void secedeMemberAdd(String memberEmail, String mTime, String secedeDesc) {
		memberDAO.insertSecedeMember(memberEmail, mTime, secedeDesc);
	}
	
	public int getPointbyEmail(MemberVO loginMember) {
		return memberDAO.getPointbyEmail(loginMember);
	}

	
}
