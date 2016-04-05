package com.ktds.oph.prohibitedWord.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class DoInsertProhibitedWordServlet
 */
public class DoInsertProhibitedWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProhibitedWordBiz prohibitedWordBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoInsertProhibitedWordServlet() {
        super();
        prohibitedWordBiz = new ProhibitedWordBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prohibitedWordName = request.getParameter("prohibitedWordName");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		prohibitedWordBiz.insertProhibitedWord(prohibitedWordName, member);
		
		response.sendRedirect(Root.get(this) + "/");
	}

}
