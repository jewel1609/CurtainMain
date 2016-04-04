package com.ktds.oph.univ.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.biz.UnivBiz;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class MassiveUnivDeleteServlet
 */
public class MassiveUnivDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MassiveUnivDeleteServlet() {
        super();
        univBiz = new UnivBiz();
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
		String[] univId = request.getParameterValues("univId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		univBiz.deleteUnivs(univId, member);
		
		response.sendRedirect(Root.get(this) + "/univList");
	}

}
