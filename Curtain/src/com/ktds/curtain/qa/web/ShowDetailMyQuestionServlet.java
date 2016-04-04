package com.ktds.curtain.qa.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.qa.biz.QuestionAndAnswerBiz;
import com.ktds.curtain.qa.vo.QuestionAndAnswerVO;

/**
 * Servlet implementation class ShowMyQuestionDetailServlet
 */
public class ShowDetailMyQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailMyQuestionServlet() {
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
		QuestionAndAnswerVO question = questionAndAnswerBiz.getMyQuestionByQuestionId(questionId);
		
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.DETAIL_QUESTION);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_DETAIL_QUESTION, member.getNickName(), questionId) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION, question.getQuestionTitle(), member.getNickName(), question.getQuestionDescription() ));
		
		historyBiz.addHistory(historyVO);
		
		
		request.setAttribute("question", question);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/qa/detailMyQuestion.jsp");
		rd.forward(request, response);
	
	}

}
