package com.ktds.oph.major.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.biz.MajorGroupBiz;
import com.ktds.oph.major.vo.MajorGroupVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class RegisterNewMajorGroupServlet
 */
public class RegisterNewMajorGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorGroupBiz majorGroupBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterNewMajorGroupServlet() {
        super();
        majorGroupBiz = new MajorGroupBiz();
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
		String newMajorGroup = request.getParameter("newMajorGroup");
		
		MajorGroupVO majorGroupVO = new MajorGroupVO();
		majorGroupVO.setMajorGroupName(newMajorGroup);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		boolean registerMajorGroup = majorGroupBiz.registerMajorGroup(majorGroupVO, member);
		
		if (registerMajorGroup) {
			response.sendRedirect(Root.get(this) + "/majorGroupList");
			return;
		}
	}

}
