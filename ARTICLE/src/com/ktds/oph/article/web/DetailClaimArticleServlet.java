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

/**
 * Servlet implementation class DetailClaimArticleServlet
 */
public class DetailClaimArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private ArticleVO claimArticleInfo;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailClaimArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        claimArticleInfo = new ArticleVO();
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
		System.out.println(articleId);
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		claimArticleInfo = articleBiz.getClaimArticleInfoByArticleId(articleId, member);
		
		request.setAttribute("claimArticleInfo", claimArticleInfo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detailClaimArticle.jsp");
		rd.forward(request, response);
	}

}
