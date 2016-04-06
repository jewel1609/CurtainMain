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
 * Servlet implementation class DetailArticleServlet
 */
public class DetailArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;   
	private ArticleVO articleVO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
        articleVO = new ArticleVO();
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
		
		articleVO = articleBiz.getArticleInfoByArticleId(articleId, member);
		
		request.setAttribute("articleInfo", articleVO);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detailArticle.jsp");
		rd.forward(request, response);
	}

}
