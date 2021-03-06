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
import com.ktds.curtain.file.vo.FileVO;
import com.ktds.curtain.member.vo.MemberVO;

/**
 * Servlet implementation class UpdateArticleServlet
 */
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleBiz articleBiz;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateArticleServlet() {
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
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		HttpSession session = request.getSession();
		MemberVO stdMember = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleVO articleVO = new ArticleVO();
		articleVO = articleBiz.showDetail(articleId, stdMember);
		String desc = articleVO.getArticleDesc().replaceAll("<br/>", "\n");
		articleVO.setArticleDesc(desc);
		articleVO.setBoardId(boardId);
		
		List<FileVO> files = articleBiz.getFileListByArticleId(articleId);
		
		request.setAttribute("article", articleVO);
		request.setAttribute("files", files);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/updateArticle.jsp");
		rd.forward(request,response);
	}

}
