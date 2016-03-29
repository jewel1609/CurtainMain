package com.ktds.curtain.survey.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.survey.vo.SurveyVO;

/**
 * Servlet implementation class SurveyServlet
 */
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyBiz surveyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
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
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		System.out.println ( mTime );
		
		
		SurveyVO survey = surveyBiz.showTodaySurvey(mTime);
		
		request.setAttribute("survey", survey);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/common/rightMenu.jsp");
		rd.forward(request, response);
	}

}
