package com.ktds.oph.major.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.vo.MajorGroupSearchVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class InitMajorServlet
 */
public class InitMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitMajorServlet() {
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
		MajorGroupSearchVO majorSearchVO = (MajorGroupSearchVO) session.getAttribute("_SEARCH_");
		int majorGroupId = majorSearchVO.getMajorGroupId();
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.INIT_SEARCH);
		historyVO.setDescription( BuildDescription.get(Description.INIT_SEARCH ));
		
		historyBiz.addHistory(historyVO);
		session.removeAttribute("_SEARCH_");
		
		response.sendRedirect(Root.get(this)+"/detailMajor?majorGroupId="+majorGroupId);
	}

}