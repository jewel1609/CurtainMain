package com.ktds.curtain.survey.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.survey.vo.SurveyVO;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoTodaySurveyServlet
 */
public class DoTodaySurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyBiz surveyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoTodaySurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String userSelectSurvey = request.getParameter("todaySurvey");
		
		String survey1 = request.getParameter("survey1");
		String survey2 = request.getParameter("survey2");
		String survey3 = request.getParameter("survey3");
		String survey4 = request.getParameter("survey4");
		
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		System.out.println(surveyId);
		
		
		System.out.println(surveyId);
		int choiceNumber = 0;
		
		if ( userSelectSurvey.equals(survey1) ) {
			choiceNumber = 1;
		}
		else if ( userSelectSurvey.equals(survey2) ) {
			choiceNumber = 2;
		}
		else if ( userSelectSurvey.equals(survey3) ) {
			choiceNumber = 3;
		}
		else if ( userSelectSurvey.equals(survey4) ) {
			choiceNumber = 4;
		}
		System.out.println(choiceNumber);
		surveyBiz.statsUpdateTodaySurvey(choiceNumber, surveyId, request);
		
		response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
	}

}
