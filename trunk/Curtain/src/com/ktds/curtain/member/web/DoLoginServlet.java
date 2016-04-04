package com.ktds.curtain.member.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private SurveyBiz surveyBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
        surveyBiz = new SurveyBiz();
        historyBiz = new OperationHistoryBiz();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 접근입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberVO member = new MemberVO();
		member.setEmail(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPassword"));
		String autoLogin = request.getParameter("autoLoginCheckBox");
		String checkCount = null;
		boolean surveyCheck = false;
		
		
		if (memberBiz.isExistMember(member, request)) {
			if(autoLogin != null) {
				memberBiz.setCookie(member, response);
			}
			else {
				memberBiz.removeCookie(member, response);
			}
			
		HttpSession session = request.getSession();
		member = (MemberVO) session.getAttribute("_MEMBER_");
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		surveyCheck = surveyBiz.surveyCheck(member.getEmail() , mTime);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.LOGIN);
		historyVO.setDescription( BuildDescription.get(Description.LOGIN, member.getNickName()));
		
		historyBiz.addHistory(historyVO);
		
		
		

			if ( surveyCheck) {
				//투표를 이미했다는 것이기때문에
				//결과물만 보여준다.
				checkCount = "a";
				session.setAttribute("_VOTE_", checkCount);
				if(member.getMemberTypeId() == 1 || member.getMemberTypeId() == 2 || member.getMemberTypeId() == 3 ){
					response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
					session.setAttribute("_BOARD_ID_", 1);
				} 
				else{
					response.sendRedirect(Root.get(this) + "/promotionArticle");
					session.setAttribute("_BOARD_ID_", 3);
				}
			}
		
			else if(!surveyCheck ){
				session.setAttribute("_VOTE_", checkCount);
				if(member.getMemberTypeId() == 1 || member.getMemberTypeId() == 2 || member.getMemberTypeId() == 3){
					response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
					session.setAttribute("_BOARD_ID_", 1);
				}
				else {
					response.sendRedirect(Root.get(this) + "/promotionArticle");
					session.setAttribute("_BOARD_ID_", 3);
				}
			}
		}
		
		else {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail("");
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.LOGIN);
			historyVO.setDescription( BuildDescription.get(Description.LOGIN_FAIL, request.getRemoteHost() ,member.getEmail()));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect(Root.get(this) + "/main.jsp?errorCode=1");
		}
		
	}

}

