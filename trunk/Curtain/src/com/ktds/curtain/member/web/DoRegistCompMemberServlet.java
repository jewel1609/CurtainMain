package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class RegistCompMemberServlet
 */
public class DoRegistCompMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberBiz compMemberBiz;    
	private MemberVO memberVO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistCompMemberServlet() {
        super();
        compMemberBiz = new MemberBiz();
        memberVO = new MemberVO();
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
		String inputCompPassword = request.getParameter("inputCompPassword");
		String inputCompName = request.getParameter("inputCompName");
		int inputPhoneNum = Integer.parseInt(request.getParameter("inputPhoneNum"));
		String inputCompSecondEmail = request.getParameter("inputCompSecondEmail");
		
		compMemberBiz.addCompMember(inputCompEmail,inputCompPassword,inputCompName
									,inputPhoneNum,inputCompSecondEmail);
		
		memberVO.setEmail(inputCompEmail);
		memberVO.setPassword(inputCompPassword);
		memberVO.setNickName("yo");
		memberVO.setSecondEmail(inputCompSecondEmail);
		memberVO.setMemberTypeId(4);
		memberVO.setPoint(0);
		memberVO.setCompanyName(inputCompName);
		memberVO.setPhoneNumber(inputPhoneNum);
		memberVO.setSignupDate(compMemberBiz.getDateTimeByEmail(inputCompEmail));
		
		HttpSession session = request.getSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		response.sendRedirect(Root.get(this) + "/doLogin");
	}

}
