package com.ktds.oph.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.member.vo.MemberVO;
import com.ktds.oph.operationHistory.biz.OperationHistoryBiz;
import com.ktds.oph.operationHistory.vo.ActionCode;
import com.ktds.oph.operationHistory.vo.BuildDescription;
import com.ktds.oph.operationHistory.vo.Description;
import com.ktds.oph.operationHistory.vo.OperationHistoryVO;

/**
 * Servlet implementation class DetailClaimArticleServlet
 */
public class DetailClaimArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private ArticleVO claimArticleInfo;
    private OperationHistoryBiz historyBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailClaimArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        claimArticleInfo = new ArticleVO();
        historyBiz = new OperationHistoryBiz();
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
		String articleId = request.getParameter("articleId");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		claimArticleInfo = articleBiz.getClaimArticleInfoByArticleId(articleId, member);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(member.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.CLAIM_DETAIL_PAGE);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_CLAIM_DETAIL_PAGE, member.getEmail()));
		historyVO.setEtc( BuildDescription.get(Description.DETAIL_CLAIM_SHOW, articleId));
		
		historyBiz.addHistory(historyVO);
		
		request.setAttribute("claimArticleInfo", claimArticleInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detailClaimArticle.jsp");
		rd.forward(request, response);
	}

}
