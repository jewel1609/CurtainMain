package com.ktds.curtain.articleLike.web;

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
 * Servlet implementation class LikeServlet
 */
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleLikeBiz articleLikeBiz;
	private DislikeBiz dislikeBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        articleLikeBiz = new ArticleLikeBiz();
        dislikeBiz = new DislikeBiz();
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
		
		String likeArticleId = request.getParameter("articleId");
		int articleId = Integer.parseInt(likeArticleId.substring(4));
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
//		boardId
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		ArticleDislikeVO dislikeVO = new ArticleDislikeVO();
		dislikeVO.setArticleId(articleId);
		dislikeVO.setEmail(member.getEmail());
		dislikeVO.setBoardId(boardId);
		boolean isExistDislikeData = dislikeBiz.isExistDislikeData(dislikeVO);
		boolean isExistLikeData = false;
		int updateLikeCount = 0;
		
		ArticleLikeVO articleLikeVO = new ArticleLikeVO();
		articleLikeVO.setArticleId(articleId);
		articleLikeVO.setBoradId(boardId);
		
		if (!isExistDislikeData) {
			articleLikeBiz.insertOrDeleteLikeData(articleLikeVO, member);
			isExistLikeData = articleLikeBiz.isExistLikeData(articleLikeVO, member);
			updateLikeCount = articleLikeBiz.getArticleLikes(articleLikeVO);
		}
		
// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"doLike\" : "+ isExistLikeData);
		json.append(", \"updateLikeCount\" : "+ updateLikeCount);
		json.append(", \"articleId\" : "+ articleId);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
		
	}

}
