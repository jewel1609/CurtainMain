package com.ktds.curtain.member.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class RegistCompMemberServlet
 */
public class DoRegistCompMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz compMemberBiz;    
	private MemberVO memberVO;
    private ProhibitedWordBiz prohibitedWordBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistCompMemberServlet() {
        super();
        compMemberBiz = new MemberBiz();
        memberVO = new MemberVO();
        prohibitedWordBiz = new ProhibitedWordBiz();
        historyBiz= new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputCompEmail = request.getParameter("inputCompEmail");
		String inputCompPassword = request.getParameter("inputCompPassword");
		String inputCompName = request.getParameter("inputCompName");
		String inputPhoneNum = request.getParameter("inputPhoneNum");
		String inputCompSecondEmail = request.getParameter("inputCompSecondEmail");
		String compNickName = inputCompName+"의 관리자";
		
		compMemberBiz.addCompMember(inputCompEmail,inputCompPassword,inputCompName
									,inputPhoneNum,inputCompSecondEmail);
		
		memberVO.setEmail(inputCompEmail);
		memberVO.setPassword(inputCompPassword);
		memberVO.setNickName(compNickName);
		memberVO.setSecondEmail(inputCompSecondEmail);
		memberVO.setMemberTypeId(4);
		memberVO.setPoint(0);
		memberVO.setCompanyName(inputCompName);
		memberVO.setPhoneNumber(inputPhoneNum);
		memberVO.setSignupDate(compMemberBiz.getDateTimeByEmail(inputCompEmail));
		
		//각 게시판 참여 인원
		memberVO.setNoticeBoardMemberCount(compMemberBiz.getNoticeBoardMemberCount());

		//비방글 필터
		List<List<String>> wordList = prohibitedWordBiz.getProhibitedWordList();		
		
		HttpSession session = request.getSession();
		session.setAttribute("_MEMBER_", memberVO);
		session.setAttribute("_WORDLIST_", wordList);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(inputCompEmail);
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.REGIST_COMP_MEMBER);
		historyVO.setDescription( BuildDescription.get(Description.REGIST_COMP_MEMBER, request.getRemoteHost()) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_REGIST_COMP_MEMBER, inputCompName, inputPhoneNum, inputCompEmail, compNickName ));
		
		historyBiz.addHistory(historyVO);
		
		
		response.sendRedirect(Root.get(this) + "/doLogin");
	}

}
