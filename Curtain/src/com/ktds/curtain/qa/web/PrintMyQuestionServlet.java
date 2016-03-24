package com.ktds.curtain.qa.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class PrintMyQuestionServlet
 */
public class PrintMyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PrintMyQuestionServlet() {
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
		
		// TODO 준호 세션에서 이메일을 받아와야 한다.
		// 기업 회원인지 학생 회원인지 구분을 해주어야 한다.
		String email = "sosdig@naver.com";
		List<QuestionAndAnswerVO> questions = questionAndAnswerBiz.getMyQuestionsByStudentEmail(email);
		
		request.setAttribute("questions", questions);
	}

}
