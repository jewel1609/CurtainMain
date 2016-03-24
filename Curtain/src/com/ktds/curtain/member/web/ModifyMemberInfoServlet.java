package com.ktds.curtain.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.member.biz.StdMemberBiz;

/**
 * Servlet implementation class ModifyMemberInfoServlet
 */
public class ModifyMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StdMemberBiz memberBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberInfoServlet() {
        super();
        memberBiz = new StdMemberBiz();
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
		
		String userEmail = request.getParameter("userEmail");
		String userNickName = request.getParameter("userNickName");
		
		memberBiz.modifyMemberInfo(userEmail, userNickName);
		
		
		
	
	
	}

}
