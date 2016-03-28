package com.ktds.curtain.qa.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class PrintMyQuestionServlet
 */
public class ShowMyQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMyQuestionsServlet() {
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
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		List<QuestionAndAnswerVO> questions = questionAndAnswerBiz.getMyQuestionsByEmail(member);
		request.setAttribute("questions", questions);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/qa/myQuestion.jsp");
		rd.forward(request, response);
	}

}
