package com.ktds.oph.questionAndAnswer.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.oph.questionAndAnswer.biz.QuestionAndAnswerBiz;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class QuestionAndAnswerServlet
 */
public class QuestionAndAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionAndAnswerBiz questionAndAnswerBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionAndAnswerServlet() {
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
		List<QuestionAndAnswerVO> questions = questionAndAnswerBiz.getAllQuestions(request);
		request.setAttribute("questions", questions);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/questionAndAnswer/questionAndAnswer.jsp");
		rd.forward(request, response);
	}

}
