package com.ktds.curtain.qa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class ShowMyQuestionDetailServlet
 */
public class ShowDetailMyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailMyQuestionServlet() {
        super();
        questionAndAnswerBiz = new QuestionAndAnswerBiz();
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
		String questionId = request.getParameter("questionId");
		QuestionAndAnswerVO question = questionAndAnswerBiz.getMyQuestionByQuestionId(questionId);
		request.setAttribute("question", question);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/qa/detailMyQuestion.jsp");
		rd.forward(request, response);
	
	}

}
