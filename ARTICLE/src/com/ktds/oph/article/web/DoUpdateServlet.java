package com.ktds.oph.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.oph.article.biz.ArticleBiz;
import com.ktds.oph.article.vo.ArticleVO;
import com.ktds.oph.util.MultipartHttpServletRequest;

/**
 * Servlet implementation class DoUpdateServlet
 */
public class DoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleBiz articleBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUpdateServlet() {
        super();
        articleBiz = new ArticleBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "수정 불가합니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// mutilpartHttpServletResquest 선언
		MultipartHttpServletRequest multipartRequest = new MultipartHttpServletRequest(request);
		boolean doUpdate = articleBiz.doUpdate(multipartRequest);
		
		if ( doUpdate ) {
			response.sendRedirect("/list");
		}
		else {
			response.sendRedirect("/detail");
		}
		
	}

}
