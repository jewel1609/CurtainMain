package com.ktds.oph.prohibitedWord.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
import com.ktds.oph.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class DoInsertProhibitedWordServlet
 */
public class DoInsertProhibitedWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProhibitedWordBiz prohibitedWordBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoInsertProhibitedWordServlet() {
        super();
        prohibitedWordBiz = new ProhibitedWordBiz();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prohibitedWordName = request.getParameter("prohibitedWordName");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		prohibitedWordBiz.insertProhibitedWord(prohibitedWordName, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.PROHIBITED_WORD_ADD);
		historyVO.setDescription( BuildDescription.get(Description.PROHIBITED_WORD_ADD, member.getEmail()));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_PROHIBITED_WORD_ADD, prohibitedWordName));
		
		historyBiz.addHistory(historyVO);
		
		response.sendRedirect(Root.get(this) + "/");
	}

}
