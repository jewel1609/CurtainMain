package com.ktds.oph.article.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ArticleListVO;
import com.ktds.oph.article.vo.ArticleSearchVO;
import com.ktds.oph.article.vo.ArticleVO;

/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private List<ArticleVO> aritcles;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        articleBiz = new ArticleBiz();
        aritcles = new ArrayList<ArticleVO>();
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
		int pageNo = 0;
		
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		catch (NumberFormatException nfe) {}
		
		ArticleSearchVO searchVO = new ArticleSearchVO();
		searchVO.setPageNo(pageNo);
		
		ArticleListVO article = articleBiz.getAllArticle(searchVO);
		
		request.setAttribute("articles", article);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/list.jsp");
		rd.forward(request, response);
	}

}
