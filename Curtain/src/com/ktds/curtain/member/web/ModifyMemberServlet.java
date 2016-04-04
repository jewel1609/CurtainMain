package com.ktds.curtain.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.dupl.biz.DuplBiz;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.biz.MemberBiz;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class ModifyMemberServlet
 */
public class ModifyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
	private DuplBiz duplBiz;
	private OperationHistoryBiz historyBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyMemberServlet() {
        super();
        memberBiz = new MemberBiz();
        duplBiz = new DuplBiz();
        historyBiz= new OperationHistoryBiz();
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
		String memberPassword = member.getPassword();
		
		int countArticle = memberBiz.countArticle(member); 
		int countReply = memberBiz.countReply(member);
		int countSurvey = memberBiz.countSurvey(member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.MODIFY_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_MODIFY_PAGE, member.getNickName()));
		
		historyBiz.addHistory(historyVO);
		
		
		request.setAttribute("countArticle", countArticle);
		request.setAttribute("countReply", countReply);
		request.setAttribute("countSurvey", countSurvey);
		
		request.setAttribute("member", member);
		request.setAttribute("univName", univName);
		request.setAttribute("majorName", majorName);
		request.setAttribute("majorGroupName", majorGroupName);
		request.setAttribute("memberRank", memberRank);
		request.setAttribute("memberPassword", memberPassword);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp");
		rd.forward(request, response);
		
	}

}
