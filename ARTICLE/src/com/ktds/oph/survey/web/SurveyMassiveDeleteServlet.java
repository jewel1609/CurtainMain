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
 * Servlet implementation class SurveyMassiveDeleteServlet
 */
public class SurveyMassiveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SurveyBiz surveyBiz;   
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyMassiveDeleteServlet() {
        super();
        surveyBiz = new SurveyBiz();
        historyBiz= new OperationHistoryBiz();
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
		String[] deleteSurveyId = request.getParameterValues("surveyId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		surveyBiz.deleteSurvey(deleteSurveyId, member);
		
		for(int i=0; i<deleteSurveyId.length; i++) {
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.SURVEY_DELETE);
			historyVO.setDescription( BuildDescription.get(Description.DO_SURVEY_DELETE, member.getEmail()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_SURVEY_DELETE, deleteSurveyId[i]));
			
			historyBiz.addHistory(historyVO);
		}
		
		response.sendRedirect(Root.get(this) + "/surveyList");
		
	}

}
