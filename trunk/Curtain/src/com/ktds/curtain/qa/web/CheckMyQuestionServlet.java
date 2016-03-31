package com.ktds.curtain.qa.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class CheckMyQuestionServlet
 */
public class CheckMyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionAndAnswerBiz questionAndAnswerBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMyQuestionServlet() {
        super();
        questionAndAnswerBiz = new QuestionAndAnswerBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 접근입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionId = request.getParameter("questionId");
		
		questionAndAnswerBiz.isSetIsCheckedByQuestionId(questionId);
		
		response.sendRedirect(Root.get(this) + "/showDetailMyQuestion?questionId=" + questionId);
	}

}
