package com.ktds.oph.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberListVO;
import com.ktds.oph.member.vo.MemberSearchVO;
import com.ktds.oph.member.vo.MemberVO;

/**
 * Servlet implementation class DetailMemberServlet
 */
public class DetailMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz; 
	private MemberVO memberInfo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailMemberServlet() {
        super();
        memberBiz = new MemberBiz();
        memberInfo = new MemberVO();
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
		
		String memberEmail = request.getParameter("memberEmail");
		System.out.println(memberEmail);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		memberInfo = memberBiz.getMemberInfoByEmail(memberEmail, member);
		
		request.setAttribute("member", memberInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/detailMember.jsp");
		rd.forward(request, response);
	}

}