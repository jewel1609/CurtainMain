package com.ktds.curtain.reply.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.prohibitedWord.biz.ProhibitedWordBiz;
import com.ktds.curtain.reply.biz.ReplyBiz;
import com.ktds.curtain.reply.vo.ReplyVO;

/**
 * Servlet implementation class DoWriteReplyServlet
 */
public class DoWriteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyBiz replyBiz;
	private ProhibitedWordBiz proBiz;
	private ArticleBiz articleBiz;
	private OperationHistoryBiz historyBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoWriteReplyServlet() {
        super();
        replyBiz = new ReplyBiz();
        proBiz = new ProhibitedWordBiz();
        articleBiz = new ArticleBiz();
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
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int parentReplyId = Integer.parseInt(request.getParameter("parentReplyId"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		String reply = request.getParameter("replyDesc");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		List<List<String>> wordList = (List<List<String>>) session.getAttribute("_WORDLIST_");

		ArticleVO articleVO = articleBiz.showDetail(articleId, member);
		if (articleVO.getBoardId()!=Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)) {
			for (int i = 0; i < wordList.size(); i++) {
				List<String> words = new ArrayList<String>();
				words = wordList.get(i);
				for ( String word : words) {
					if (reply.contains(word)) {
						response.sendRedirect("/showDetail?isFword=1&articleId="+articleId+"&boardId="+articleVO.getBoardId());
						return;
					}
				}
			}
		}
		
		
		ReplyVO replyInfo = new ReplyVO();
		replyInfo.setArticleId(articleId);
		replyInfo.setParentReplyId(parentReplyId);
		replyInfo.setReplyOrder(orderNo);
		replyInfo.setReplyId(replyId);
		replyInfo.setReplyDesc(reply);
		

		if ( member != null ) {
			replyInfo.setEmail(member.getEmail());
		}
		
		replyBiz.addNewReply(replyInfo, request);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.DO_REPLY);
		historyVO.setDescription( BuildDescription.get(Description.DO_REPLY, member.getNickName(), request.getParameter("articleId") ) );
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_DO_REPLY, replyInfo.getReplyDesc()));
		
		historyBiz.addHistory(historyVO);
		
		response.sendRedirect("/showDetail?articleId="+articleId+"&boardId="+articleVO.getBoardId());
	}

}
