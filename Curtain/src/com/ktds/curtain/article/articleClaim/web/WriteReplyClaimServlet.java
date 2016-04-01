package com.ktds.curtain.article.articleClaim.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.articleClaim.biz.WriteClaimBiz;
import com.ktds.curtain.article.articleClaim.vo.ArticleClaimVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class WriteReplyClaimServlet
 */
public class WriteReplyClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WriteClaimBiz writeClaimBiz;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteReplyClaimServlet() {
        super();
        writeClaimBiz = new WriteClaimBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String claimText = request.getParameter("claimText");
		String replyClaim = request.getParameter("replyClaim");

		int replyId = Integer.parseInt(replyClaim.substring(5));
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		ArticleClaimVO articleClaimVO = new ArticleClaimVO();
		articleClaimVO.setReplyId(replyId);
		articleClaimVO.setEmail(member.getEmail());
		articleClaimVO.setClaimText(claimText);
		
		boolean doWriteClaim = writeClaimBiz.doWriteReplyClaim(articleClaimVO);

		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
	}

}
