package com.ktds.curtain.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.member.vo.StdMemberVO;

/**
 * Servlet implementation class WriteArticleServlet
 */
public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteArticleServlet() {
        super();
        articleBiz = new ArticleBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못 된 요청입니다.");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleTitle = request.getParameter("articleTitle");
		String articleDescription = request.getParameter("articleDescription");
		String articleTypeName = request.getParameter("articleTypeName");
		String articleTypeId = request.getParameter("articleTypeId");
		
//		HttpSession session = request.getSession();
//		StdMemberVO stdMember = (StdMemberVO) session.getAttribute("_STU_MEMBER_");
		
		ArticleVO article = new ArticleVO();
		article.setArticleTitle(articleTitle);
		article.setArticleDesc(articleDescription);
		article.setArticleTypeName(articleTypeName);
		article.setArticleTypeId(Integer.parseInt(articleTypeId));
		
		article.setStudentEmail("test@smu.ac.kr");
		article.setMemberTypeId(1);
		article.setUnivId(1);
		article.setMajorGroupId(68);
		article.setMajorId(1);
		article.setNickName("류연s");
		article.setPassword("1");

		boolean doWriteArticle = articleBiz.doWriteArticle(article);


	}

}
