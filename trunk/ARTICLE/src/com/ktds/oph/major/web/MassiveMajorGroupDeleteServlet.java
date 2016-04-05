package com.ktds.oph.major.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.major.biz.MajorGroupBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class MassiveMajorGroupDeleteServlet
 */
public class MassiveMajorGroupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorGroupBiz majorGroupBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MassiveMajorGroupDeleteServlet() {
        super();
        majorGroupBiz = new MajorGroupBiz();
        
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
		String[] majorGroupId = request.getParameterValues("majorGroupId");
		
		for (int i = 0; i < majorGroupId.length; i++) {
			System.out.println(majorGroupId[i]);
		}
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		majorGroupBiz.deleteMajorGroups(majorGroupId, member);
		
		response.sendRedirect(Root.get(this) + "/majorGroupList");
	}

}
