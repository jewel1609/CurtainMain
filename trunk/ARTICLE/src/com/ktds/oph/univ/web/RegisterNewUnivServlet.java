package com.ktds.oph.univ.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.univ.biz.UnivBiz;
import com.ktds.oph.univ.vo.UnivVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class RegisterNewUnivServlet
 */
public class RegisterNewUnivServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UnivBiz univBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterNewUnivServlet() {
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
		String newUniv = request.getParameter("newUniv");
		
		UnivVO univVO = new UnivVO();
		univVO.setUnivName(newUniv);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean registerUniv = univBiz.registerUniv(univVO, member);
		
		if (registerUniv) {
			response.sendRedirect(Root.get(this) + "/univList");
			return;
		}
			
	}

}
