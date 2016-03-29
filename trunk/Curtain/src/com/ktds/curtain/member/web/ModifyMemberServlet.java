package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.dupl.biz.DuplBiz;
import com.ktds.curtain.dupl.vo.DuplVO;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ModifyMemberServlet
 */
public class ModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
	private DuplBiz duplBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberServlet() {
        super();
        memberBiz = new MemberBiz();
        duplBiz = new DuplBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session을 받아온다.
		HttpSession session = request.getSession();
		
		// Object로 넘어오기 때문에 MemberVO로 캐스팅해준다.
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String univName = memberBiz.getUnivName(member.getUnivId());
		String majorName = memberBiz.getMajorName(member.getMajorId());
		String majorGroupName = memberBiz.getMajorGroupName(member.getMajorGroupId());
		String memberRank = memberBiz.getMemberRank(member.getMemberTypeId());
		
		request.setAttribute("member", member);
		request.setAttribute("univName", univName);
		request.setAttribute("majorName", majorName);
		request.setAttribute("majorGroupName", majorGroupName);
		request.setAttribute("memberRank", memberRank);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp");
		rd.forward(request, response);
		
	}

}
