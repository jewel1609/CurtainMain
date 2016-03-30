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
 * Servlet implementation class DoRegistMemberServlet
 */
public class DoRegistStdMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberBiz stdMemberBiz;  
    private MemberVO memberVO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoRegistStdMemberServlet() {
        super();
        stdMemberBiz = new MemberBiz();
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
		String inputUnivEmail = request.getParameter("inputUnivEmail");
		String inputPassword = request.getParameter("inputPassword");
		String inputUnivName = request.getParameter("inputUnivName");
		String inputMajorName = request.getParameter("inputMajorName");
		String inputSecondEmail = request.getParameter("inputSecondEmail");
		
		stdMemberBiz.addStdMember(inputUnivEmail,inputPassword,inputUnivName
									,inputMajorName,inputSecondEmail);
		
		memberVO.setEmail(inputUnivEmail);
		memberVO.setPassword(inputPassword);
		memberVO.setNickName(stdMemberBiz.getNickNameByEmail(inputUnivName));
		memberVO.setSecondEmail(inputSecondEmail);
		memberVO.setMemberTypeId(1);
		memberVO.setPoint(1000);
		memberVO.setMajorGroupId(stdMemberBiz.getMajorGroupIdByMajorId(stdMemberBiz.getMajorIdByMajorName(inputMajorName)));
		memberVO.setMajorId(stdMemberBiz.getMajorIdByMajorName(inputMajorName));
		memberVO.setSignupDate(stdMemberBiz.getDateTimeByEmail(inputUnivEmail));
		
		HttpSession session = request.getSession();
		session.setAttribute("_MEMBER_", memberVO);
		
		response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
	}

}
