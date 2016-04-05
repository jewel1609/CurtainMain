package com.ktds.oph.prohibitedWord.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

/**
 * Servlet implementation class InsertProhibitedWordServlet
 */
public class InsertProhibitedWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OperationHistoryBiz historyBiz;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProhibitedWordServlet() {
        super();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(loginMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ADMIN_PROHIBITED_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_PROHIBITED_PAGE, loginMember.getEmail()));
		
		historyBiz.addHistory(historyVO);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/prohibitedWord/insertProhibitedWord.jsp");
		rd.forward(request, response);
	}

}
