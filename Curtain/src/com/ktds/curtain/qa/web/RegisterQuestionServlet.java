package com.ktds.curtain.qa.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class RegisterQuestionServlet
 */
public class RegisterQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterQuestionServlet() {
        super();
        questionAndAnswerBiz = new QuestionAndAnswerBiz();
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

		QuestionAndAnswerVO questionAndAnswerVO = new QuestionAndAnswerVO();
		
		String memberType = request.getParameter("memberType");
		questionAndAnswerVO.setQuestionDescription(request.getParameter("questionDescription"));
		questionAndAnswerVO.setQuestionTitle(request.getParameter("questionTitle"));
		// 기업회원인지 학생회원인지
		// 세션에서 받아와야 한다.
		if (memberType.equals("1")) {
			questionAndAnswerVO.setStudentEmail("sosdig@naver.com");
		}
		else if (memberType.equals("2")){
			questionAndAnswerVO.setCompanyEmail("sosdig@daum.net");
		}
		
		
		
	}

}
