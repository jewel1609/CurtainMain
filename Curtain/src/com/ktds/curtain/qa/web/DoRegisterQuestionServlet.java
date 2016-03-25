package com.ktds.curtain.qa.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class RegisterQuestionServlet
 */
public class DoRegisterQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegisterQuestionServlet() {
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
		
		// TODO 준호 세션에서 이메일을 받아와야 한다.
//		HttpSession session = request.getSession();
//		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
//		questionAndAnswerVO.setEmail(member.getEmail());
//		questionAndAnswerVO.setMemberTypeId(member.getMemberTypeId());
		questionAndAnswerVO.setEmail("sosdig@naver.com");
		questionAndAnswerVO.setMemberTypeId(1);
		
		questionAndAnswerVO.setQuestionDescription(request.getParameter("questionDescription"));
		questionAndAnswerVO.setQuestionTitle(request.getParameter("questionTitle"));
		
		questionAndAnswerBiz.registerQuestion(questionAndAnswerVO);
		
		response.sendRedirect(Root.get(this) + "/questionAndAnswer");
		
	}

}
