package com.ktds.curtain.auth.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.auth.biz.MacBiz;

/**
 * Servlet implementation class CompAuthNumCheckServlet
 */
public class CompAuthNumCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MacBiz macBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompAuthNumCheckServlet() {
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
		
		boolean isExistsCompEmail = macBiz.checkAuthNumByUnivEmail(inputUnivEmail,inputNumberCheck);
		
		if(isExistsCompEmail){
			macBiz.deleteAuthNumByUnivEmail(inputUnivEmail);
			
			StringBuffer json = new StringBuffer();
			PrintWriter out = response.getWriter();
			
			json.append("{");
			json.append("\"result\" : true");
			json.append(", \"isCompEmail\" : " + isExistsCompEmail);
			json.append("}");
			
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
