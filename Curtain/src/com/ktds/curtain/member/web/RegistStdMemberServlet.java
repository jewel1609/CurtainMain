package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;

/**
 * Servlet implementation class RegistMemberServlet
 */
public class RegistStdMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistStdMemberServlet() {
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
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail("");
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.REGIST_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_REGIST_PAGE, request.getRemoteHost()) );
		
		historyBiz.addHistory(historyVO);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/registMember.jsp");
		rd.forward(request, response);
		
	}

}
