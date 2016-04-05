package com.ktds.oph.history.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.history.biz.HistoryBiz;
import com.ktds.oph.history.vo.HistoryListVO;
import com.ktds.oph.history.vo.HistorySearchVO;
import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

/**
 * Servlet implementation class HistoryListServlet
 */
public class HistoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryListServlet() {
        super();
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
			
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(loginMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ADMIN_HISTORY_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_HISTORY_PAGE, loginMember.getEmail()));
		
		historyBiz.addHistory(historyVO);
	
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/history/historyList.jsp");
		rd.forward(request, response);
	}

}
