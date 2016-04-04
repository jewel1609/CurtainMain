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
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class WriteClaimServlet
 */
public class WriteClaimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WriteClaimBiz writeClaimBiz;
	private OperationHistoryBiz historyBiz; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteClaimServlet() {
        super();
        writeClaimBiz = new WriteClaimBiz();
        historyBiz = new OperationHistoryBiz();
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
		String articleClaim = request.getParameter("articleClaim");

		int articleId = Integer.parseInt(articleClaim.substring(5));
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		ArticleClaimVO articleClaimVO = new ArticleClaimVO();
		articleClaimVO.setArticleId(articleId);
		articleClaimVO.setEmail(member.getEmail());
		articleClaimVO.setClaimText(claimText);
		
		boolean doWriteClaim = writeClaimBiz.doWriteClaim(articleClaimVO);

		if ( doWriteClaim ) {
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setEmail(member.getEmail());
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.DO_CLAIM_ARTICLE);
			historyVO.setDescription( BuildDescription.get(Description.DO_CLAIM_ARTICLE, member.getNickName(), request.getParameter("articleId") ) );
			
			historyBiz.addHistory(historyVO);
		}
		
		
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
