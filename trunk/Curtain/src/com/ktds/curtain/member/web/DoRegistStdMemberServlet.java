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
 * Servlet implementation class DoRegistMemberServlet
 */
public class DoRegistStdMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberBiz stdMemberBiz;  
    private MemberVO memberVO;
    private ProhibitedWordBiz prohibitedWordBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistStdMemberServlet() {
        super();
        stdMemberBiz = new MemberBiz();
        memberVO = new MemberVO();
        prohibitedWordBiz = new ProhibitedWordBiz();
        historyBiz = new OperationHistoryBiz();
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
		String inputUnivEmail = request.getParameter("inputUnivEmail");
		String inputPassword = request.getParameter("inputPassword");
		String inputUnivName = request.getParameter("inputUnivName");
		String inputMajorName = request.getParameter("inputMajorName");
		String inputSecondEmail = request.getParameter("inputSecondEmail");
		
		stdMemberBiz.addStdMember(inputUnivEmail,inputPassword,inputUnivName
									,inputMajorName,inputSecondEmail);
		
		memberVO.setEmail(inputUnivEmail);
		memberVO.setPassword(inputPassword);
		memberVO.setNickName(stdMemberBiz.getNickNameByEmail(inputUnivName));
		memberVO.setSecondEmail(inputSecondEmail);
		memberVO.setMemberTypeId(1);
		memberVO.setPoint(1000);
		memberVO.setMajorGroupId(stdMemberBiz.getMajorGroupIdByMajorId(stdMemberBiz.getMajorIdByMajorName(inputMajorName)));
		memberVO.setMajorId(stdMemberBiz.getMajorIdByMajorName(inputMajorName));
		memberVO.setSignupDate(stdMemberBiz.getDateTimeByEmail(inputUnivEmail));
		
		//각 게시판 참여 인원
		memberVO.setMajorMemberCount(stdMemberBiz.getMajorMemberCountByMajorGroupId(memberVO.getMajorGroupId()));
		memberVO.setUnivMemberCount(stdMemberBiz.getUnivMemberCountByUnivId(memberVO.getUnivId()));
		memberVO.setNoticeBoardMemberCount(stdMemberBiz.getNoticeBoardMemberCount());
		memberVO.setFreeBoardMemberCount(stdMemberBiz.getFreeBoardMemberCount());
		memberVO.setOneLayerCurtainMemberCount(stdMemberBiz.getOneLayerCurtainMemberCount());
		memberVO.setTwoLayerCurtainMemberCount(stdMemberBiz.getTwoLayerCurtainMemberCount());
		
		//비방글 필터
		List<String> wordList = prohibitedWordBiz.getProhibitedWordList();
		
		HttpSession session = request.getSession();
		session.setAttribute("_MEMBER_", memberVO);
		session.setAttribute("_WORDLIST_", wordList);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(inputUnivEmail);
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.REGIST_STD_MEMBER);
		historyVO.setDescription( BuildDescription.get(Description.REGIST_STD_MEMBER, request.getRemoteHost()) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_REGIST_STD_MEMBER, inputUnivName, inputMajorName, inputUnivEmail, memberVO.getNickName() ));
		
		historyBiz.addHistory(historyVO);
		
		
		response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
	}

}
