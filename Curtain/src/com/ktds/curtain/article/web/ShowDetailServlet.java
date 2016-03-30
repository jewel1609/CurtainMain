package com.ktds.curtain.article.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.file.vo.FileVO;

/**
 * Servlet implementation class ShowDetailServlet
 */
public class ShowDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDetailServlet() {
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

		int isFword = 0;

		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		System.out.println(articleId);
		
		ArticleVO article = articleBiz.showDetail(articleId);
		List<FileVO> files = articleBiz.getFileListByArticleId(articleId);

		try {
			isFword = Integer.parseInt(request.getParameter("isFword"));
			request.setAttribute("isFword", isFword);
		}
		catch (NumberFormatException nfe) {
			isFword = 0;
		}
		finally {
			request.setAttribute("article", article);
			request.setAttribute("files", files);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detail.jsp");
			rd.forward(request, response);
			
		}

	}

}
