package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.Root;

import oracle.net.aso.s;

/**
 * Servlet implementation class DoLoginServlet
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
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
	
		
		if (memberBiz.isExistMember(member, request)) {
			if(autoLogin != null) {
				memberBiz.setCookie(member, response);
			}
			else {
				memberBiz.removeCookie(member, response);
			}
			response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
		}
		else {
			response.sendRedirect(Root.get(this) + "/main.jsp?errorCode=1");
		}
		
	}

}
