package com.ktds.oph.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberListVO;
import com.ktds.oph.member.vo.MemberSearchVO;

/**
 * Servlet implementation class ShowMemberServlet
 */
public class ShowMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMemberServlet() {
        super();
        memberBiz = new MemberBiz();
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
		int pageNo = 0;
		
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		catch (NumberFormatException nfe) {}
		
		MemberSearchVO searchVO = new MemberSearchVO();
		searchVO.setPageNo(pageNo);
		
		MemberListVO members = memberBiz.getAllMember(searchVO);
		
		request.setAttribute("members", members);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/memberList.jsp");
		rd.forward(request, response);
	}

}
