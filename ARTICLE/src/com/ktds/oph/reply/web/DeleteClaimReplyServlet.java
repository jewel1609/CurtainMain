package com.ktds.oph.reply.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.reply.biz.ReplyBiz;

/**
 * Servlet implementation class DeleteClaimReplyServlet
 */
public class DeleteClaimReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyBiz replyBiz;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClaimReplyServlet() {
        super();
        replyBiz = new ReplyBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못 된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyId = request.getParameter("replyId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		replyBiz.deleteReply(replyId, member);
		
		response.sendRedirect("/showClaimArticle");
	}

}
