package com.ktds.oph.questionAndAnswer.web;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class DoModifyAnswerServlet
 */
public class DoModifyAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionAndAnswerBiz questionAndAnswerBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModifyAnswerServlet() {
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
		
		QuestionAndAnswerVO question = new QuestionAndAnswerVO();
		
		boolean isSuccess = false;
		String modifyAnswer = "";
		try {
			question.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
			modifyAnswer = request.getParameter("modifyAnswer");
			modifyAnswer = modifyAnswer.replace("\n", "<br />");
			question.setAnswerDescription(modifyAnswer);
			
			isSuccess = questionAndAnswerBiz.modifyAnswer(question, member);
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.MODIFY_ANSWER);
			historyVO.setDescription( BuildDescription.get(Description.MODIFY_ANSWER, member.getEmail(), request.getParameter("questionId")));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_MODIFY_ANSWER, request.getParameter("modifyAnswer")));
			
			historyBiz.addHistory(historyVO);
			
		}
		catch (NumberFormatException nfe) {
		}
		
		if ( isSuccess == false ) {
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert(\"질문자가 글을 읽었거나 관리자가 아니라 글 수정에 실패했습니다.!\");");
			out.println("location.href=\"showQuestion\"");
			out.println("</script>");
			out.close();
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/detailQuestion");
			rd.forward(request, response);
		}
	}
}
