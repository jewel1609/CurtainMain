package com.ktds.curtain.articleDislike.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.curtain.articleDislike.biz.DislikeBiz;
import com.ktds.curtain.articleDislike.vo.DislikeVO;
import com.ktds.curtain.member.vo.StdMemberVO;

/**
 * Servlet implementation class DislikeServlet
 */
public class DislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DislikeBiz dislikeBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DislikeServlet() {
        super();
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
	
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		System.out.println("articleId : " + articleId);
		
		HttpSession session = request.getSession();
		StdMemberVO member = (StdMemberVO) session.getAttribute("_MEMBER_");
		
		DislikeVO dislikeVO = new DislikeVO();
		dislikeVO.setArticleId(articleId);
		dislikeVO.setStudentEmail("shinmi@curtain.ac.kr");
		//TODO 나중에 세션값 받아와서 바꿔주어야함
		//dislikeVO.setStudentEmail(member.getStudentEmail());
		
		dislikeVO.setBoardId(4);
		//TODO jsp에서 넘겨받아와서 바꿔주어야함
		
		dislikeBiz.insertOrDeleteDislikeData(dislikeVO);
		
		//TODO 히스토리 체크
		
		boolean isExistDislikeData = dislikeBiz.isExistDislikeData(dislikeVO);
		int updateDislikeCount = dislikeBiz.getArticleDislikes(dislikeVO);
		System.out.println(updateDislikeCount);
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isDislike\" : " + isExistDislikeData);
		json.append(", \"updateDislikeCount\" : "+ updateDislikeCount);
		json.append(", \"articleId\" : " +articleId);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.print( json.toString() );
		out.flush();
		out.close();
		
		
		
	}

}
