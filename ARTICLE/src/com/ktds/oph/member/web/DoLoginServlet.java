package com.ktds.oph.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private MemberVO loginMember;
	private String memberId;
	private String memberPassword;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
        loginMember = new MemberVO();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		memberId = request.getParameter("userId");
		memberPassword = request.getParameter("userPw");
		
		doLoginMember();
		
		boolean isLoginSuccess = isLoginSuccess(request);
		
		// 로그인 성공 여부에 따라
		if ( isLoginSuccess ) {
			// TODO 쿠키 체크
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.LOGIN_SUCCESS);
			historyVO.setDescription( BuildDescription.get(Description.LOGIN_SUCCESS, request.getRemoteHost()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_LOGIN_SUCCESS, loginMember.getEmail(), loginMember.getPassword()));
			
			historyBiz.addHistory(historyVO);
			
			
			response.sendRedirect("/successLogin");
			return;
		}
		else {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.LOGIN_FAIL);
			historyVO.setDescription( BuildDescription.get(Description.LOGIN_FAIL, request.getRemoteHost()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_LOGIN_FAIL, memberId, memberPassword));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/?errorCode=1");
		}
	}
	
	/**
	 * 로그인 체크
	 * @param request
	 * @return
	 */
	private boolean isLoginSuccess (HttpServletRequest request) {
		
		return memberBiz.login(loginMember, request);
	}
	
	/**
	 * memberVO에 담기
	 * @return
	 */
	private MemberVO doLoginMember() {
		
		loginMember.setEmail(memberId);
		loginMember.setPassword(memberPassword);
		
		return loginMember;
	}

}
