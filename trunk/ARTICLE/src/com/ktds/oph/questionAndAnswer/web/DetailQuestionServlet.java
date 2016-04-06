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
import com.ktds.oph.questionAndAnswer.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class DetailQuestionServlet
 */
public class DetailQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailQuestionServlet() {
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
		
		String questionId = request.getParameter("questionId");
		QuestionAndAnswerVO question = questionAndAnswerBiz.getQuestionByQuestionId(questionId);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.QUESTION_DETAIL_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_QUESTION_DETAIL_PAGE, member.getEmail()));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_QUESTION, questionId, question.getQuestionTitle(), question.getQuestionDescription(), question.getQuestionDate()));
		
		historyBiz.addHistory(historyVO);
		
		request.setAttribute("question", question);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/questionAndAnswer/detailQuestion.jsp");
		rd.forward(request, response);
	}

}
