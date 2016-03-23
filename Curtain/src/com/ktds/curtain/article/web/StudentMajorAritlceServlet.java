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
import com.ktds.curtain.member.vo.StdMemberVO;

/**
 * Servlet implementation class StudentMajorAritlceServlet
 */
public class StudentMajorAritlceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentMajorAritlceServlet() {
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
		
		HttpSession session = request.getSession();
		StdMemberVO stdMember = (StdMemberVO) session.getAttribute("_STU_MEMBER_");
		
		List<ArticleVO> majorArticles = articleBiz.showMajorArticle(stdMember);
		
		request.setAttribute("majorArticles", majorArticles);
		RequestDispatcher rd = request.getRequestDispatcher("//WEB-INF/view/article/article.jsp");
		rd.forward(request, response);
	}

}
