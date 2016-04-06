package com.ktds.oph.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class MassiveModifyServlet
 */
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberBiz memberBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        memberBiz = new MemberBiz();
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
		String modifyMemberEmail = request.getParameter("memberEmail");
		String modifyMemberTypeId = request.getParameter("memberTypeId");
		String modifyMemberPoint = request.getParameter("memberPoint");
		String modifyMemberPassword = request.getParameter("memberPassword");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		int beforeMemberTypeId = member.getMemberTypeId();
		
		memberBiz.modifyMember(modifyMemberEmail,modifyMemberTypeId,modifyMemberPoint, modifyMemberPassword,member);
		
		if(beforeMemberTypeId != Integer.parseInt(modifyMemberTypeId)){
			memberBiz.modifyRankDate(modifyMemberEmail, member);
		}
		
		response.sendRedirect(Root.get(this) + "/showMember");
	}

}
