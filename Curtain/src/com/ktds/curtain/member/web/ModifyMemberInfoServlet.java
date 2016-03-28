package com.ktds.curtain.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ModifyMemberInfoServlet
 */
public class ModifyMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberInfoServlet() {
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
		
		// session을 받아온다.
		HttpSession session = request.getSession();
		
		// Object로 넘어오기 때문에 MemberVO로 캐스팅해준다.
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		System.out.println(member.getEmail());
		String userEmail = request.getParameter("userEmail");
		String userNickName = request.getParameter("userNickName");
		
		member.setSecondEmail(userEmail);
		member.setNickName(userNickName);
		
		session.setAttribute("_MEMBER_", member);
		
		memberBiz.modifyMemberInfo(userEmail, userNickName, member.getEmail());
		
		
		response.sendRedirect("/myPage");
	
	}

}
