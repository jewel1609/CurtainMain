package com.ktds.oph.questionAndAnswer.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.questionAndAnswer.biz.QuestionAndAnswerBiz;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerListVO;
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerSearchVO;

/**
 * Servlet implementation class QuestionAndAnswerServlet
 */
public class ShowQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionAndAnswerBiz questionAndAnswerBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowQuestionServlet() {
        super();
        questionAndAnswerBiz = new QuestionAndAnswerBiz();
        historyBiz = new OperationHistoryBiz();
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
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ADMIN_QUESTION_PAGE);
		
		int pageNo = 0;
		
		QuestionAndAnswerSearchVO searchVO = new QuestionAndAnswerSearchVO();
		session  = request.getSession();
		
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			searchVO.setPageNo(pageNo);
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			searchVO.setSearchType(request.getParameter("searchType"));
			
			historyVO.setDescription( BuildDescription.get(Description.LIST_PAGING, member.getEmail(), pageNo+""));
		}
		catch (NumberFormatException nfe) {
			historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_QUESTION_PAGE, member.getEmail()));
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
		
		historyBiz.addHistory(historyVO);
		
		request.setAttribute("questionList", questionList);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("isSuccess", request.getParameter("isSuccess"));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/questionAndAnswer/questionAndAnswer.jsp");
		rd.forward(request, response);
	}

}
