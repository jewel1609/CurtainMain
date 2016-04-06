package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ModifyMemberPasswordServlet
 */
public class ModifyMemberPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberBiz memberBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberPasswordServlet() {
        super();
        memberBiz = new MemberBiz();
        historyBiz = new OperationHistoryBiz();
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
		
		
		if ( userPw.equals(userPassword) && userNewPw.equals(userNewRePw) ) {
			memberBiz.modifyMemberPassword(userNewPw, email);
			member.setPassword(userNewPw);
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.MODIFY_MEMBER_PASSWORD);
			historyVO.setDescription( BuildDescription.get(Description.MODIFY_MEMBER_PASSWORD, member.getNickName()) );
			historyVO.setEtc(BuildDescription.get(Description.DETAIL_MEMBER_PASSWORD, userNewPw ));
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/myPage");
			return;
		}
		else {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.MODIFY_MEMBER_PASSWORD_FAIL);
			historyVO.setDescription( BuildDescription.get(Description.MODIFY_MEMBER_PASSWORD_FAIL, member.getNickName()) );
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/?errorCode=1");
			return;
		}
		
		
		
	}

}
