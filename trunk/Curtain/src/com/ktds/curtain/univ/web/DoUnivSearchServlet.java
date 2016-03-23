package com.ktds.curtain.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.univ.biz.UnivBiz;

/**
 * Servlet implementation class DoUnivSearchServlet
 */
public class DoUnivSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UnivBiz univBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUnivSearchServlet() {
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
		
		String inputUnivName = request.getParameter("inputUnivName");
		String checkUnivName = univBiz.checkUnivName(inputUnivName);
		System.out.println("대학이름 : " + inputUnivName);
		
		StringBuffer json = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		json.append("{");
		json.append("\"checkUnivName\" : \""+ checkUnivName +"\"");
		
		System.out.println("넘길거 : " + checkUnivName);
		json.append("}");
		
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
