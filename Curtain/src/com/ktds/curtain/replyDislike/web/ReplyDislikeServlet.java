package com.ktds.curtain.replyDislike.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.replyDislike.biz.ReplyDislikeBiz;
import com.ktds.curtain.replyDislike.vo.ReplyDislikeVO;
import com.ktds.curtain.replyLike.biz.ReplyLikeBiz;
import com.ktds.curtain.replyLike.vo.ReplyLikeVO;

/**
 * Servlet implementation class ReplyDislikeServlet
 */
public class ReplyDislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyDislikeBiz replyDislikeBiz;
	private ReplyLikeBiz replyLikeBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDislikeServlet() {
        super();
        replyDislikeBiz = new ReplyDislikeBiz();
        replyLikeBiz = new ReplyLikeBiz();
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
		String likeReplyId = request.getParameter("replyId");
		int replyId = Integer.parseInt(likeReplyId.substring(7));
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		ReplyLikeVO replyLikeVO = new ReplyLikeVO();
		replyLikeVO.setArticleId(articleId);
		replyLikeVO.setEmail(member.getEmail());
		replyLikeVO.setReplyId(replyId);
		boolean isExistLikeReply = replyLikeBiz.isExistLikeReply(replyLikeVO);
		boolean isExistDislikeReply = false;
		int updateDislikeCount = 0;
		
		ReplyDislikeVO replyDislikeVO = new ReplyDislikeVO();
		replyDislikeVO.setArticleId(articleId);
		replyDislikeVO.setEmail(member.getEmail());
		replyDislikeVO.setReplyId(replyId);
		
		if (!isExistLikeReply) {
			replyDislikeBiz.insertOrDeleteDislikeReply(replyDislikeVO);
			isExistDislikeReply = replyDislikeBiz.isExistDislikeReply(replyDislikeVO);
			updateDislikeCount = replyDislikeBiz.getReplyDislikes(replyDislikeVO);
		}
		
// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isDislike\" : "+ isExistDislikeReply);
		json.append(", \"updateDislikeCount\" : "+ updateDislikeCount);
		json.append(", \"replyId\" : "+ replyId);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
		
	}

}
