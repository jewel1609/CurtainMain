package com.ktds.curtain.articleLike.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.curtain.articleLike.biz.ArticleLikeBiz;
import com.ktds.curtain.articleLike.vo.ArticleLikeVO;


/**
 * Servlet implementation class LikeServlet
 */
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleLikeBiz articleLikeBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
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
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
//		boardId
//		HttpSession session = request.getSession();
//		MemberVO member = (MemberVO)session.getAttribute("_MEMBER_");
		
		ArticleLikeVO articleLikeVO = new ArticleLikeVO();
		articleLikeVO.setArticleId(articleId);
		articleLikeVO.setStudentEmail("test@smu.ac.kr");
		articleLikeVO.setBoradId(boardId);
		articleLikeBiz.insertOrDeleteLikeData(articleLikeVO);
		
		boolean isExistLikeData =  articleLikeBiz.isExistLikeData(articleLikeVO);
		int updateLikeCount = articleLikeBiz.getArticleLikes(articleLikeVO);
		System.out.println(updateLikeCount);
		
// json 만드는 방법 "{ \"key\" : \"value\" }"
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"doLike\" : "+isExistLikeData);
		json.append(", \"updateLikeCount\" : "+updateLikeCount);
		json.append(", \"articleId\" : "+articleId);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print(json.toString()); //String으로 바껴서 간다.
		out.flush();
		out.close();
		
	}

}
