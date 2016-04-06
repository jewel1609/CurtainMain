package com.ktds.oph.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.biz.MemberBiz;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;
import com.ktds.oph.util.Root;

/**
 * Servlet implementation class MassiveDeleteServlet
 */
public class MassiveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberBiz memberBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MassiveDeleteServlet() {
        super();
        memberBiz = new MemberBiz();
        historyBiz = new OperationHistoryBiz();
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
		String[] deleteMemberEmail = request.getParameterValues("memberEmail");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		System.out.println("deleteList : " + deleteMemberEmail[0]);
		memberBiz.deleteMembers(deleteMemberEmail, member);
		
		for ( int i = 0; i < deleteMemberEmail.length; i++) {
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.ADMIN_MEMBER_DELETE);
			historyVO.setDescription( BuildDescription.get(Description.DO_ADMIN_MEMBER_DELETE, member.getEmail()));
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_MEMBER_DELETE, deleteMemberEmail ));
			
			historyBiz.addHistory(historyVO);
		}
		
		
		response.sendRedirect(Root.get(this) + "/showMember");
	}

}
