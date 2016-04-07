package com.ktds.curtain.reply.web;

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
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.reply.biz.ReplyBiz;
import com.ktds.curtain.reply.vo.ReplyVO;

/**
 * Servlet implementation class DoDeleteReplyServlet
 */
public class DoDeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyBiz replyBiz;   
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoDeleteReplyServlet() {
        super();
        replyBiz = new ReplyBiz();
        historyBiz = new OperationHistoryBiz();
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
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		int replyId = Integer.parseInt(request.getParameter("delete"));
		int articleId = Integer.parseInt(request.getParameter("articleId2"));
		
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReplyId(replyId);
		replyVO.setArticleId(articleId);
		
		boolean deleteReply = replyBiz.doDeleteReply(replyVO);
		
		
		
		if ( deleteReply ) {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.DELETE_REPLY);
			historyVO.setDescription( BuildDescription.get(Description.DELETE_REPLY, member.getNickName(), articleId+"", replyId+"" ) );
			historyVO.setEtc( BuildDescription.get(Description.DETAIL_DELETE_REPLY) );
			
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/showDetail?articleId="+articleId);
			return;
		}
		
	}

}
