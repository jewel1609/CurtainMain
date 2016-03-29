package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.auth.biz.MacBiz;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoSendPasswordServlet
 */
public class DoSendPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MacBiz macBiz;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSendPasswordServlet() {
        super();
        macBiz = new MacBiz();
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
		String email = request.getParameter("email");
		String secondEmail = request.getParameter("secondEmail");
		
		System.out.println(email);
		System.out.println(secondEmail);
		
		if(macBiz.sendPasswordByEamil(email,secondEmail)){
			response.sendRedirect(Root.get(this) + "/main.jsp");
		}
		else if(!macBiz.sendPasswordByEamil(email,secondEmail)){
			response.sendRedirect(Root.get(this) + "/findPassword");
		}
	}

}
