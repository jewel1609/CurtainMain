package com.ktds.curtain.auth.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.auth.biz.MacBiz;

/**
 * Servlet implementation class AuthNumCheckServlet
 */
public class AuthNumCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MacBiz macBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthNumCheckServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String inputUnivEmail = request.getParameter("inputUnivEmail");
		String inputNumberCheck = request.getParameter("inputNumberCheck");
		System.out.println("메일 확인 :" + inputUnivEmail);
		System.out.println("넣는 인증번호 확인 : " + inputNumberCheck);
		
		boolean isExistsUnivEmail = macBiz.checkAuthNumByUnivEmail(inputUnivEmail,inputNumberCheck);
		
		
		StringBuffer json = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isUnivEmail\" : " + isExistsUnivEmail);
		json.append("}");
		
		out.print(json.toString());
		out.flush();
		out.close();
		
	}

}
