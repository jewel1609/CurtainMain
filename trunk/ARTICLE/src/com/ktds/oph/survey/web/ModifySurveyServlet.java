package com.ktds.oph.survey.web;

import java.io.IOException;

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
import com.ktds.oph.survey.biz.SurveyBiz;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class ModifySurveyServlet
 */
public class ModifySurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyBiz surveyBiz;
	private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifySurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
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
		String surveyId = request.getParameter("surveyId");
		String surveyTitle = request.getParameter("surveyTitle");
		String surveyAnswer1 = request.getParameter("surveyAnswer1");
		String surveyAnswer2 = request.getParameter("surveyAnswer2");
		String surveyAnswer3 = request.getParameter("surveyAnswer3");
		String surveyAnswer4 = request.getParameter("surveyAnswer4");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		surveyBiz.modifySurvey(surveyId,surveyTitle,surveyAnswer1,surveyAnswer2,surveyAnswer3,surveyAnswer4,member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MODIFY_SURVEY);
		historyVO.setDescription( BuildDescription.get(Description.MODIFY_SURVEY, member.getEmail()));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_MODIFY_SURVEY, surveyId, surveyTitle, surveyAnswer1, surveyAnswer2, surveyAnswer3, surveyAnswer4));
		
		historyBiz.addHistory(historyVO);
		
		response.sendRedirect(Root.get(this) + "/surveyList");
	}

}
