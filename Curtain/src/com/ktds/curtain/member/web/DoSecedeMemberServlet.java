package com.ktds.curtain.member.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
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
import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class DoSecedeMemberServlet
 */
public class DoSecedeMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
	private OperationHistoryBiz historyBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoSecedeMemberServlet() {
        super();
        memberBiz = new MemberBiz();
        historyBiz = new OperationHistoryBiz();
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

		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
			
		
		String memberEmail = request.getParameter("memberEmail");
		String secedeDesc = request.getParameter("secedeDesc");
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy/MM/dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format ( currentTime );
		
		System.out.println(memberEmail+"<탈퇴회원 이메일");
		System.out.println(mTime+"<탈퇴한 날짜");
		System.out.println(secedeDesc + "<탈퇴사유");
		
		
		memberBiz.secedeMember(memberEmail);
		memberBiz.secedeMemberAdd(memberEmail, mTime, secedeDesc);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.SECEDE_MEMBER);
		historyVO.setDescription( BuildDescription.get(Description.SECEDE_MEMBER, member.getNickName()) );
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_SECEDE_MEMBER, member.getEmail(), secedeDesc ));
		
		historyBiz.addHistory(historyVO);
		
		request.getSession().invalidate();
		response.sendRedirect(Root.get(this) + "/main.jsp");
	}

}
