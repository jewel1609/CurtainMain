package com.ktds.oph.questionAndAnswer.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.questionAndAnswer.biz.QuestionAndAnswerBiz;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerListVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerSearchVO;
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
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		int pageNo = 0;
		
		QuestionAndAnswerSearchVO searchVO = new QuestionAndAnswerSearchVO();
		
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			searchVO.setPageNo(pageNo);
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			searchVO.setSearchType(request.getParameter("searchType"));
		}
		catch (NumberFormatException nfe) {
			searchVO = (QuestionAndAnswerSearchVO) session.getAttribute("_QUESTION_SEARCH_");
			if (searchVO == null) {
				searchVO = new QuestionAndAnswerSearchVO();
				searchVO.setPageNo(0);
				searchVO.setSearchKeyword("");
				searchVO.setSearchType("1");
			}
		}
		
		session.setAttribute("_QUESTION_SEARCH_", searchVO);
		
		QuestionAndAnswerListVO questionList = questionAndAnswerBiz.getQuestionList(searchVO, member);
		
		request.setAttribute("questionList", questionList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/questionAndAnswer/questionAndAnswer.jsp");
		rd.forward(request, response);
	}

}
