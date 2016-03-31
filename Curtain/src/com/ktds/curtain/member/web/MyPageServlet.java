package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class MyPageServlet
 */
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberBiz memberBiz;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        memberBiz = new MemberBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// session을 받아온다.
			HttpSession session = request.getSession();
			
			// Object로 넘어오기 때문에 MemberVO로 캐스팅해준다.
			MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
			
			String univName = memberBiz.getUnivName(member.getUnivId());
			String majorName = memberBiz.getMajorName(member.getMajorId());
			String memberRank = memberBiz.getMemberRank(member.getMemberTypeId());
			int countArticle = memberBiz.countArticle(member); 
			int countReply = memberBiz.countReply(member);
			int countSurvey = memberBiz.countSurvey(member);
			
			
			request.setAttribute("countArticle", countArticle);
			request.setAttribute("countReply", countReply);
			request.setAttribute("countSurvey", countSurvey);
			
			
			
			request.setAttribute("member", member);
			request.setAttribute("univName", univName);
			request.setAttribute("majorName", majorName);
			request.setAttribute("memberRank", memberRank);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/myPageIndex.jsp");
			rd.forward(request, response);
	}

}
