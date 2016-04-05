package com.ktds.oph.survey.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.survey.biz.SurveyBiz;
import com.ktds.oph.survey.vo.SurveyListVO;
import com.ktds.oph.survey.vo.SurveySearchVO;

/**
 * Servlet implementation class SurveyListServlet
 */
public class SurveyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;      
	private SurveyBiz surveyBiz;
	private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyListServlet() {
        super();
        memberBiz = new MemberBiz();
        surveyBiz = new SurveyBiz();
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
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		if(!memberBiz.isAdmin(loginMember)){
			response.setContentType("text/html; charset=UTF-8");
			 
			PrintWriter out = response.getWriter();
			 
			out.println("<script>"); 
			out.println("alert('관리자가 아닙니다.');"); 
			out.println("window.history.back();");
			out.println("</script>"); 
			out.close();
		}
		else if(memberBiz.isAdmin(loginMember)){
			
			int pageNo = 0;
			
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			catch (NumberFormatException nfe) {}
			
			SurveySearchVO searchVO = new SurveySearchVO();
			searchVO.setPageNo(pageNo);
			
			SurveyListVO survey = surveyBiz.getAllSurvey(searchVO);
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(loginMember.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.ADMIN_SURVEY_LIST_PAGE);
			historyVO.setDescription( BuildDescription.get(Description.VISIT_ADMIN_SURVEY_LIST_PAGE, loginMember.getEmail()));
			
			historyBiz.addHistory(historyVO);
			
			
			request.setAttribute("survey", survey);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/survey/surveyList.jsp");
			rd.forward(request, response);
		}
	}

}
