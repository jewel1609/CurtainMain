package com.ktds.curtain.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class RegistCompMemberServlet
 */
public class DoRegistCompMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz stdMemberBiz;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistCompMemberServlet() {
        super();
        stdMemberBiz = new MemberBiz();
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
		String inputCompEmail = request.getParameter("inputCompEmail");
		String inputCompPassword = request.getParameter("inputCompPassword");
		String inputCompName = request.getParameter("inputCompName");
		int inputPhoneNum = Integer.parseInt(request.getParameter("inputPhoneNum"));
		String inputCompSecondEmail = request.getParameter("inputCompSecondEmail");
		
		stdMemberBiz.addCompMember(inputCompEmail,inputCompPassword,inputCompName
									,inputPhoneNum,inputCompSecondEmail);
		
		response.sendRedirect(Root.get(this) + "/main.jsp");
	}

}
