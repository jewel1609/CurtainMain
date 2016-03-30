package com.ktds.curtain.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;

/**
 * Servlet implementation class HitsCountServlet
 */
public class HitsCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HitsCountServlet() {
        super();
        articleBiz = new ArticleBiz();
        
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
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		System.out.println(boardId);
		System.out.println(articleId);
		
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticleId(articleId);
		articleVO.setBoardId(boardId);
		
		articleBiz.hitsCount(articleVO);
		
		if ( boardId == Integer.parseInt(BoardId.MAJOR_BOARD)) {
			response.sendRedirect("/showDetail?articleId="+articleId);
		}
		else if ( boardId == Integer.parseInt(BoardId.UNIV_BOARD)) {
			response.sendRedirect("/studentUnivArticle");
		}
		else if ( boardId == Integer.parseInt(BoardId.SECRET_BOARD_LEVEL1)) {
			response.sendRedirect("/showDetail?articleId="+articleId);
		}
		else if( boardId == Integer.parseInt(BoardId.SECRET_BOARD_LEVEL2)){
			
		}
		else if( boardId == Integer.parseInt(BoardId.SECRET_BOARD_LEVEL3)){
			
		}
	}

}
