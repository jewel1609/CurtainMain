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
 * Servlet implementation class ModifyMemberInfoServlet
 */
public class ModifyMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperationHistoryBiz historyBiz;
       
	private MemberBiz memberBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberInfoServlet() {
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
		
		// session을 받아온다.
		HttpSession session = request.getSession();
		
		// Object로 넘어오기 때문에 MemberVO로 캐스팅해준다.
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		String userEmail = request.getParameter("userEmail");
		String userNickName = request.getParameter("userNickName");
		
		if ( userEmail == null || userEmail == "" ) {
			userEmail = member.getSecondEmail();
		}
		
		if (userNickName == null || userNickName == "") {
			userNickName = member.getNickName();
		}
		
		String beforeNickName = member.getNickName();
		member.setSecondEmail(userEmail);
		member.setNickName(userNickName);
		
		session.setAttribute("_MEMBER_", member);
		
		memberBiz.modifyMemberInfo(userEmail, userNickName, member.getEmail());
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MODIFY_MEMBER_INFO);
		historyVO.setDescription( BuildDescription.get(Description.MODIFY_MEMBER_INFO, beforeNickName) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_MEMBER_INFO, userEmail, userNickName ));
		
		historyBiz.addHistory(historyVO);
		
		
		
		response.sendRedirect("/myPage");
	
	}

}
