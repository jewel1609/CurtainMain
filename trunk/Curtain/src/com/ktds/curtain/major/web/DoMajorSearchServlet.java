package com.ktds.curtain.major.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.major.biz.MajorBiz;

/**
 * Servlet implementation class DoMajorSearchServlet
 */
public class DoMajorSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MajorBiz majorBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoMajorSearchServlet() {
        super();
        majorBiz = new MajorBiz();
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
		String inputMajorName = request.getParameter("inputMajorName");
		String checkMajorNameList = majorBiz.checkMajorName(inputMajorName);
		
		StringBuffer json = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		json.append("{");
		json.append("\"checkMajorNameList\" : \""+ checkMajorNameList +"\"");
		json.append("}");
		
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
