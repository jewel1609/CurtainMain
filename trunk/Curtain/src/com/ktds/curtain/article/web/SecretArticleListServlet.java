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
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.articleDislike.biz.DislikeBiz;
import com.ktds.curtain.member.vo.MemberVO;


/**
 * Servlet implementation class SecretArticleListServlet
 */
public class SecretArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleBiz articleBiz;
	private DislikeBiz dislikeBiz;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecretArticleListServlet() {
		super();
		articleBiz = new ArticleBiz();
		dislikeBiz = new DislikeBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		session.setAttribute("_BOARD_ID_", 4);
		
		List<ArticleVO> secretArticles = articleBiz.showSecretArticle(stdMember, BoardId.FREE_BOARD);
		ArticleVO topArticle = articleBiz.showTopArticle(stdMember, BoardId.FREE_BOARD);
		
		request.setAttribute("topArticle", topArticle);
		request.setAttribute("secretArticles", secretArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/article/secretArticle.jsp");
		rd.forward(request, response);

	}

}
