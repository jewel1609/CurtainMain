package com.ktds.curtain.survey.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.survey.biz.SurveyBiz;

/**
 * Servlet implementation class DoSurveyServlet
 */
public class DoSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyBiz surveyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSurveyServlet() {
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
		
		String userSelectSurvey = request.getParameter("selectRadio");
		String survey1 = request.getParameter("survey1");
		String survey2 = request.getParameter("survey2");
		String survey3 = request.getParameter("survey3");
		String survey4 = request.getParameter("survey4");
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		boolean isCheckId = false;
		
		if ( userSelectSurvey == null) {
			isCheckId = false;
			System.out.println(userSelectSurvey);
			System.out.println(survey1 + "=======서베이");
		}
		else if ( userSelectSurvey != null ){
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
			System.out.println("========" + choiceNumber);
			
			if ( choiceNumber > 0 ) { 
				isCheckId = surveyBiz.statsUpdateTodaySurvey(choiceNumber, surveyId);
			}
		}	
		
		
		System.out.println(isCheckId);
		
		if ( isCheckId ) { //투포했으면
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		else { //투표안했으면
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
