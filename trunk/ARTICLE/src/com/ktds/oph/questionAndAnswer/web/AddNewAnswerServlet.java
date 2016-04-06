package com.ktds.oph.questionAndAnswer.web;

import java.io.IOException;
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
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class AddNewAnswer
 */
public class AddNewAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionAndAnswerBiz questionAndAnswerBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewAnswerServlet() {
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
		
		QuestionAndAnswerVO questionAndAnswerVO = new QuestionAndAnswerVO();
		String answerDescription = "";
		try{
			questionAndAnswerVO.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
			answerDescription = request.getParameter("answerDescription");
			answerDescription = answerDescription.replace("\n", "<br />");
			questionAndAnswerVO.setAnswerDescription(answerDescription);
			questionAndAnswerBiz.addNewAnswer(questionAndAnswerVO, member);
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.ADD_NEW_ANSWER);
			historyVO.setDescription( BuildDescription.get(Description.ADD_NEW_ANSWER, member.getEmail(), request.getParameter("questionId")));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_ADD_NEW_ANSWER, request.getParameter("answerDescription")));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect(Root.get(this) + "/detailQuestion?questionId=" + questionAndAnswerVO.getQuestionId());
		}
		catch (NumberFormatException nfe) {
			response.sendRedirect(Root.get(this) + "/showQuestion");
		}
		
	}

}
