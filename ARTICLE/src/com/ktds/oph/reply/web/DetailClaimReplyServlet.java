package com.ktds.oph.reply.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.reply.biz.ReplyBiz;
import com.ktds.oph.reply.vo.ReplyVO;

/**
 * Servlet implementation class DetailClaimReplyServlet
 */
public class DetailClaimReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyBiz replyBiz;   
    private ReplyVO claimReplyInfo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailClaimReplyServlet() {
        super();
        replyBiz = new ReplyBiz();
        claimReplyInfo = new ReplyVO();	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyId = request.getParameter("replyId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		claimReplyInfo = replyBiz.getClaimReplyInfoByReplyId(replyId, member);
		
		request.setAttribute("claimReplyInfo", claimReplyInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/reply/detailClaimReply.jsp");
		rd.forward(request, response);
	}

}
