package com.ktds.curtain.survey.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.survey.vo.SurveyVO;

/**
 * Servlet implementation class DoSurveyServlet
 */
public class DoSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SurveyBiz surveyBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSurveyServlet() {
        super();
        surveyBiz = new SurveyBiz();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN,"잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userSelectSurvey = request.getParameter("selectRadio");
		String survey1 = request.getParameter("survey1");
		String survey2 = request.getParameter("survey2");
		String survey3 = request.getParameter("survey3");
		String survey4 = request.getParameter("survey4");
		int surveyId = Integer.parseInt(request.getParameter("surveyId"));
		boolean isCheckId = false;
		String checkCount = null;
		boolean todayCheckVote =false;

		
		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		System.out.println ( mTime );
		
		SurveyVO survey = surveyBiz.showTodaySurvey(mTime);
		
		if ( member.getSurveyId() == survey.getSurveyId() ) {
			//투표를 이미했다는 것이기때문에
			//결과물만 보여준다.
			todayCheckVote = true;
			checkCount = "a";
			session.setAttribute("_VOTE_", checkCount);
		}
		
		
		
		
		
		
		if ( userSelectSurvey == null) {
			isCheckId = false;
			System.out.println(userSelectSurvey);
			System.out.println(survey1 + "=======서베이");
		}
		else if ( userSelectSurvey != null ){
			int choiceNumber = 0;
			
			if ( userSelectSurvey.equals(survey1) ) {
				choiceNumber = 1;
			}
			else if ( userSelectSurvey.equals(survey2) ) {
				choiceNumber = 2;
			}
			else if ( userSelectSurvey.equals(survey3) ) {
				choiceNumber = 3;
			}
			else if ( userSelectSurvey.equals(survey4) ) {
				choiceNumber = 4;
			}
			System.out.println("========" + choiceNumber);
			
			if ( choiceNumber > 0 ) { 
				isCheckId = surveyBiz.statsUpdateTodaySurvey(choiceNumber, surveyId);
				
				OperationHistoryVO historyVO = new OperationHistoryVO();
				historyVO.setIp(request.getRemoteHost());
				historyVO.setEmail(member.getEmail());
				historyVO.setUrl(request.getRequestURI());
				historyVO.setActionCode(ActionCode.DO_SURVEY);
				historyVO.setDescription( BuildDescription.get(Description.DO_SURVEY, member.getNickName()) );
				historyVO.setEtc(BuildDescription.get(Description.DETAIL_DO_SURVEY, userSelectSurvey ));
				
				historyBiz.addHistory(historyVO);
				
				
				if ( isCheckId == true ) {
				checkCount = "a";
				}
			}
		}	
		
		
		System.out.println(isCheckId);
		
		if ( isCheckId ) { //투포했으면
			surveyBiz.upDateIsVote(member.getEmail(), surveyId);
			session.setAttribute("_VOTE_", checkCount);
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
		else { //투표안했으면
			session.setAttribute("_VOTE_", checkCount);
			StringBuffer json = new StringBuffer(); 
			json.append("{");
			json.append("\"isCheckId\" : " + isCheckId);
			json.append("}");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
