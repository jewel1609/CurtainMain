package com.ktds.curtain.keyword.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.keyword.biz.KeywordBiz;

/**
 * Servlet implementation class KeywordServlet
 */
public class KeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KeywordBiz keywordBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeywordServlet() {
        super();
        keywordBiz = new KeywordBiz();
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
		
		ArticleVO article = new ArticleVO();
		article.setArticleTitle("안녕하세요. 우리는 하나 입니다.");
		
		keywordBiz.setNounByArticle(article);
		
		response.sendRedirect("/registStdMember");
	}

}
