package com.ktds.curtain.articleDislike.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.articleDislike.biz.DislikeBiz;
import com.ktds.curtain.articleDislike.vo.ArticleDislikeVO;
import com.ktds.curtain.articleLike.biz.ArticleLikeBiz;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;
import com.ktds.curtain.member.vo.MemberVO;


/**
 * Servlet implementation class DislikeServlet
 */
public class DislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DislikeBiz dislikeBiz;
	private ArticleLikeBiz articleLikeBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DislikeServlet() {
        super();
        dislikeBiz = new DislikeBiz();
        articleLikeBiz = new ArticleLikeBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String dislikeArticleId = request.getParameter("articleId");
		int articleId = Integer.parseInt(dislikeArticleId.substring(7));

		int boardId = Integer.parseInt(request.getParameter("boardId"));
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleDislikeVO dislikeVO = new ArticleDislikeVO();
		dislikeVO.setArticleId(articleId);
		dislikeVO.setEmail(member.getEmail());
		dislikeVO.setBoardId(boardId);
		//TODO jsp에서 넘겨받아와서 바꿔주어야함
		
		ArticleLikeVO articleLikeVO = new ArticleLikeVO();
		articleLikeVO.setArticleId(articleId);
		articleLikeVO.setBoradId(boardId);		
		// 바뀐 뒤 
		boolean isExistLikeData =  articleLikeBiz.isExistLikeData(articleLikeVO, member);
		// 전체 싫어요 수 
		int updateDislikeCount = 0;
		boolean isExistDislikeData = false;
		if (!isExistLikeData) {
			dislikeBiz.insertOrDeleteDislikeData(dislikeVO);
			isExistDislikeData = dislikeBiz.isExistDislikeData(dislikeVO);
			updateDislikeCount = dislikeBiz.getArticleDislikes(dislikeVO);
		}		
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isDislike\" : " + isExistDislikeData);
		json.append(", \"updateDislikeCount\" : "+ updateDislikeCount);
		json.append(", \"articleId\" : " + articleId);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print( json.toString() );
		out.flush();
		out.close();

	}

}
