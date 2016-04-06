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
 * Servlet implementation class DoDeleteReplyServlet
 */
public class DoDeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyBiz replyBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoDeleteReplyServlet() {
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
		int replyId = Integer.parseInt(request.getParameter("delete"));
		System.out.println(replyId);
		int articleId = Integer.parseInt(request.getParameter("articleId2"));
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyId(replyId);
		replyVO.setArticleId(articleId);
		
		boolean deleteReply = replyBiz.doDeleteReply(replyVO);
		
		if ( deleteReply ) {
			response.sendRedirect("/showDetail?articleId="+articleId);
			return;
		}
		
	}

}
