package com.ktds.oph.survey.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.survey.biz.SurveyBiz;
import com.ktds.oph.survey.vo.SurveyVO;

/**
 * Servlet implementation class DetailSurveyServlet
 */
public class DetailSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SurveyBiz surveyBiz;
	private SurveyVO surveyInfo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailSurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
        surveyInfo = new SurveyVO();
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
		
		request.setAttribute("survey", surveyInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/survey/detailSurvey.jsp");
		rd.forward(request, response);
	}

}
