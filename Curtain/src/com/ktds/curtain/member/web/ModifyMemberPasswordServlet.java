package com.ktds.curtain.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ModifyMemberPasswordServlet
 */
public class ModifyMemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberPasswordServlet() {
        super();
        memberBiz = new MemberBiz();
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
		
		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String userPassword = member.getPassword();
		String email = member.getEmail();
		
		String userPw = request.getParameter("userPw");
		String userNewPw = request.getParameter("userNewPw");
		String userNewRePw = request.getParameter("userNewRePw");
		
		System.out.println(userPw);
		System.out.println(userNewPw);
		System.out.println(userNewRePw);
		
		if ( userPw.equals(userPassword) && userNewPw.equals(userNewRePw) ) {
			System.out.println("modifyTest");
			System.out.println(email);
			memberBiz.modifyMemberPassword(userNewPw, email);
			member.setPassword(userNewPw);
			response.sendRedirect("/myPage");
			return;
		}
		else {
			response.sendRedirect("/?errorCode=1");
			return;
		}
		
		
		
	}

}
