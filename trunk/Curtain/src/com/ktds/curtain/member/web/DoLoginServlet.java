package com.ktds.curtain.member.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.survey.biz.SurveyBiz;
import com.ktds.curtain.survey.vo.SurveyVO;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
	private SurveyBiz surveyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
        surveyBiz = new SurveyBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 접근입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberVO member = new MemberVO();
		member.setEmail(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPassword"));
		String autoLogin = request.getParameter("autoLoginCheckBox");
		String checkCount = null;
		boolean surveyCheck = false;
		
		
		if (memberBiz.isExistMember(member, request)) {
			if(autoLogin != null) {
				memberBiz.setCookie(member, response);
			}
			else {
				memberBiz.removeCookie(member, response);
			}
			
		HttpSession session = request.getSession();
		
		member = (MemberVO) session.getAttribute("_MEMBER_");
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		surveyCheck = surveyBiz.surveyCheck(member.getEmail() , mTime);
		
		

		if ( surveyCheck) {
			//투표를 이미했다는 것이기때문에
			//결과물만 보여준다.
			checkCount = "a";
			session.setAttribute("_VOTE_", checkCount);
		}
			response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
		
		}
		else {
			response.sendRedirect(Root.get(this) + "/main.jsp?errorCode=1");
		}
		
	}

}

