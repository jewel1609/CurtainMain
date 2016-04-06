package com.ktds.oph.survey.web;

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
import com.ktds.oph.survey.biz.SurveyBiz;
import com.ktds.oph.survey.vo.SurveyVO;

/**
 * Servlet implementation class DetailSurveyServlet
 */
public class DetailSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyBiz surveyBiz;
	private SurveyVO surveyInfo;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailSurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
        surveyInfo = new SurveyVO();
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
		String surveyId = request.getParameter("surveyId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		surveyInfo = surveyBiz.getSurveyInfoBySurveyId(surveyId, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.DETAIL_SURVEY_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_DETAIL_SURVEY_PAGE, member.getEmail()));
		
		historyBiz.addHistory(historyVO);
		
		request.setAttribute("survey", surveyInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/survey/detailSurvey.jsp");
		rd.forward(request, response);
	}

}
