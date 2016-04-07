package com.ktds.curtain.article.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleSearchVO;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.history.biz.OperationHistoryBiz;
import com.ktds.curtain.history.vo.ActionCode;
import com.ktds.curtain.history.vo.BuildDescription;
import com.ktds.curtain.history.vo.Description;
import com.ktds.curtain.history.vo.OperationHistoryVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class PromotionArticleServlet
 */
public class PromotionArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;   
	private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromotionArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        historyBiz = new OperationHistoryBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		session.setAttribute("_BOARD_ID_", 3);
		
		ArticleSearchVO searchVO = new ArticleSearchVO();

		if(request.getParameter("searchKeyword") == null) {
			searchVO = (ArticleSearchVO) session.getAttribute("_SEARCH_");
			if (searchVO == null) {
				searchVO = new ArticleSearchVO();
				searchVO.setSearchKeyword("");
				searchVO.setSearchType("1");
			}
		}
		else {
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			searchVO.setSearchType(request.getParameter("articleTypeId"));
		}
		
		session.setAttribute("_SEARCH_", searchVO);
		
		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setEmail(stdMember.getEmail());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ARTICLE_PRO);
		historyVO.setDescription( BuildDescription.get(Description.VISIT_ARTICLE_PRO, stdMember.getNickName()));
		
		historyBiz.addHistory(historyVO);
		
		List<ArticleVO> promotionArticles = articleBiz.showPromotionArticle(stdMember, BoardId.AD_BOARD, searchVO, request);
		ArticleVO topArticle = articleBiz.showTopArticle(stdMember, BoardId.AD_BOARD);
		
		request.setAttribute("topArticle", topArticle);
		request.setAttribute("promotionArticles", promotionArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/article/promotionArticle.jsp");
		rd.forward(request, response);
	}

}
