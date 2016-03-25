package com.ktds.curtain.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.article.biz.ArticleBiz;
import com.ktds.curtain.article.vo.ArticleVO;
import com.ktds.curtain.article.vo.BoardId;
import com.ktds.curtain.member.vo.MemberVO;
import com.ktds.curtain.util.MultipartHttpServletRequest.MultipartFile;
import com.ktds.curtain.util.MultipartHttpServletRequest;


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
		request.setCharacterEncoding("UTF-8");
		String articleTitle = request.getParameter("articleTitle");
		System.out.println(articleTitle);
		String articleDescription = request.getParameter("articleDescription");
		System.out.println(articleDescription);
		String articleTypeId = request.getParameter("articleTypeId");
		System.out.println(articleTypeId);
		String boardId = request.getParameter("boardId");
		System.out.println(boardId);
		
//		HttpSession session = request.getSession();
//		StdMemberVO stdMember = (StdMemberVO) session.getAttribute("_STU_MEMBER_");
		
		ArticleVO article = new ArticleVO();
		article.setArticleTitle(articleTitle);
		article.setArticleDesc(articleDescription);
		article.setArticleTypeId(Integer.parseInt(articleTypeId));
		article.setBoardId(Integer.parseInt(boardId));
		article.setStudentEmail("test@smu.ac.kr");
		article.setMemberTypeId(1);
		article.setUnivId(1);
		article.setMajorGroupId(1);
		article.setMajorId(1);
		article.setNickName("류연s");
		article.setPassword("1");
		

		boolean doWriteArticle = articleBiz.doWriteArticle(article);
		
		if ( boardId.equals(BoardId.MAJOR_BOARD)) {
			response.sendRedirect("/studentMajorAritlce");
		}
		else if ( boardId.equals(BoardId.UNIV_BOARD)) {
			response.sendRedirect("/studentUnivArticle");
		}


	}

}
