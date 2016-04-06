package com.ktds.curtain.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.univ.biz.UnivBiz;

/**
 * Servlet implementation class DoCompCheckServlet
 */
public class DoCompCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoCompCheckServlet() {
        super();
        univBiz = new UnivBiz();
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
		boolean isExistCompEmail = univBiz.isExistStdUnivEmail(inputCompEmail);
		
		StringBuffer json = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isExistCompEmail\" : "+ isExistCompEmail);
		json.append("}");
		
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
