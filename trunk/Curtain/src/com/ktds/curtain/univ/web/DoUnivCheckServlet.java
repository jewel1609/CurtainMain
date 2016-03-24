package com.ktds.curtain.univ.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.univ.biz.UnivBiz;

/**
 * Servlet implementation class DoUnivCheckServlet
 */
public class DoUnivCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UnivBiz univBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUnivCheckServlet() {
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
		
		String inputUnivEmail = request.getParameter("inputUnivEmail");
		System.out.println("체크할 대학메일 이름 : " + inputUnivEmail);
		boolean isExistStdUnivEmail = univBiz.isExistStdUnivEmail(inputUnivEmail);
		
		StringBuffer json = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isExistStdUnivEmail\" : "+ isExistStdUnivEmail);
		json.append("}");
		System.out.println("중복 대학이메일 결과 : " + isExistStdUnivEmail);
		
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
