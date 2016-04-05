package com.ktds.oph.survey.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.survey.biz.SurveyBiz;
import com.ktds.oph.survey.vo.SurveyVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class DoInsertSurveyServlet
 */
public class DoInsertSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SurveyVO surveyVO;   
    private SurveyBiz surveyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoInsertSurveyServlet() {
        super();
        surveyVO = new SurveyVO();
        surveyBiz = new SurveyBiz();
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
		String surveyTitle = request.getParameter("surveyTitle");
		String surveyAnswer1 = request.getParameter("surveyAnswer1");
		String surveyAnswer2 = request.getParameter("surveyAnswer2");
		String surveyAnswer3 = request.getParameter("surveyAnswer3");
		String surveyAnswer4 = request.getParameter("surveyAnswer4");
		String surveyDate = request.getParameter("surveyDate");
	
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		surveyBiz.insertSurvey(surveyTitle,surveyAnswer1,surveyAnswer2,surveyAnswer3,surveyAnswer4,surveyDate, member);
		
		response.sendRedirect(Root.get(this) + "/surveyList");
		
	}

}
