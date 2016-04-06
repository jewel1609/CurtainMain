package com.ktds.curtain.auth.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.auth.biz.MacBiz;

/**
 * Servlet implementation class CompAuthNumSendServlet
 */
public class CompAuthNumSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MacBiz macBiz;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompAuthNumSendServlet() {
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
		
		String inputCompEmail = request.getParameter("inputCompEmail");
		
		boolean isSendCheck = macBiz.sendAuthEmailByUnivEmail(inputCompEmail);
		if(isSendCheck){
		
			StringBuffer json = new StringBuffer();
			json.append("{");
			json.append("\"result\" : true");
			json.append(", \"isSendCheck\" : " + isSendCheck);
			json.append("}");
			
			
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
