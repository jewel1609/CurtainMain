package com.ktds.curtain.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.util.Root;

/**
 * Servlet implementation class InitArticleListServlet
 */
public class InitArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitArticleListServlet() {
        super();
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
		HttpSession session = request.getSession();
		session.removeAttribute("_SEARCH_");
		
		int boardId = (int) session.getAttribute("_BOARD_ID_");
		
		if (boardId == 1) {
			response.sendRedirect(Root.get(this) + "/studentMajorAritlce");
		}
		else if (boardId == 2) {
			response.sendRedirect(Root.get(this) + "/studentUnivArticle");
		}
		else if (boardId == 3) {
			response.sendRedirect(Root.get(this) + "/promotionArticle");
		}
		else if (boardId == 4) {
			response.sendRedirect(Root.get(this) + "/secretArticleList");
		}
		else if (boardId == 5) {
			response.sendRedirect(Root.get(this) + "/oneLayerCurtain");
		}
		else if (boardId == 6) {
			response.sendRedirect(Root.get(this) + "/twoLayerCurtain");
		}
		else {
			response.sendRedirect(Root.get(this) + "/main.jsp");
		}
		
		
	}

}
