package com.ktds.curtain.reply.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.biz.ReplyBiz;
import com.ktds.curtain.reply.vo.ReplyVO;

/**
 * Servlet implementation class DoWriteReplyServlet
 */
public class DoWriteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyBiz replyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoWriteReplyServlet() {
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
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int parentReplyId = Integer.parseInt(request.getParameter("parentReplyId"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		String reply = request.getParameter("replyDesc");
		
		ReplyVO replyInfo = new ReplyVO();
		replyInfo.setArticleId(articleId);
		replyInfo.setParentReplyId(parentReplyId);
		replyInfo.setReplyOrder(orderNo);
		replyInfo.setReplyId(replyId);
		replyInfo.setReplyDesc(reply);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		if ( member != null ) {
			replyInfo.setEmail(member.getEmail());
		}
		
		replyBiz.addNewReply(replyInfo);
		
		response.sendRedirect("/showDetail?articleId="+articleId);
	}

}